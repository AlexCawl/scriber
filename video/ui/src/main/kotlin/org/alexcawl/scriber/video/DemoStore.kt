package org.alexcawl.scriber.video

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import org.alexcawl.mvi.core.Store

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