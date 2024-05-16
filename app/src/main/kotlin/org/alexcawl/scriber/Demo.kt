package org.alexcawl.scriber

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import org.alexcawl.mvi.compose.StoreFactoryScope
import org.alexcawl.mvi.compose.StoreScope
import org.alexcawl.mvi.core.Store
import org.alexcawl.mvi.core.StoreFactory
import javax.inject.Provider

@Composable
fun DemoApp() = StoreFactoryScope(DemoStoreFactory) {
    var isShown by remember { mutableStateOf(true) }
    Column {
        Button(
            onClick = { isShown = !isShown }
        ) {
            Text("Show Demo")
        }
        if (isShown) {
            DemoScreen()
        }
    }
}

@Composable
fun DemoScreen() = StoreScope<DemoState, DemoAction, DemoStore> {
    val state by state.collectAsState()
    DemoScreenInternal(state)
}

@Composable
private fun DemoStore.DemoScreenInternal(state: DemoState) {
    Button(
        onClick = { consume(DemoAction.Increment) }
    ) {
        when (state) {
            DemoState.Loading -> Text("Button")
            is DemoState.Main -> Text(state.number.toString())
        }
    }
}

@Immutable
sealed interface DemoState {
    data object Loading : DemoState

    data class Main(val number: Int) : DemoState
}

@Immutable
sealed interface DemoAction {
    data object Increment : DemoAction
    data object Decrement : DemoAction
}

@OptIn(DelicateCoroutinesApi::class)
class DemoStore : Store<DemoState, DemoAction>(GlobalScope, DemoState.Loading) {
    override fun createTask(event: DemoAction): suspend CoroutineScope.(DemoState) -> DemoState {
        println(event)
        println(state.value)
        return when (event) {
            DemoAction.Decrement -> { prev ->
                when (prev) {
                    DemoState.Loading -> DemoState.Main(0)
                    is DemoState.Main -> DemoState.Main(prev.number - 1)
                }
            }
            DemoAction.Increment -> { prev ->
                when (prev) {
                    DemoState.Loading -> DemoState.Main(0)
                    is DemoState.Main -> DemoState.Main(prev.number + 1)
                }
            }
        }
    }
}

object DemoStoreFactory: StoreFactory(mapOf(DemoStore::class.java to Provider { DemoStore() }))