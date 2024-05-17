package org.alexcawl.scriber

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    DemoScreen()
}

@Composable
fun DemoScreen() = StoreScope<DemoState, DemoAction, DemoStore> {
    val state by this.state.collectAsState()
    Column {
        Text(
            text = when (state) {
                DemoState.Loading -> "Loading"
                is DemoState.Main -> (state as DemoState.Main).number.toString()
            }
        )
        Row {
            Button(
                onClick = { consume(DemoAction.Increment) }
            ) {
                Text("+")
            }
            Button(
                onClick = { consume(DemoAction.Decrement) }
            ) {
                Text("-")
            }
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

object DemoStoreFactory : StoreFactory(mapOf(DemoStore::class.java to Provider { DemoStore() }))