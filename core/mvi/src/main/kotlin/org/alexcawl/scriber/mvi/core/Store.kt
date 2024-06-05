package org.alexcawl.scriber.mvi.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class Store<S, E>(
    private val scope: CoroutineScope,
    initialState: S
) : Disposable, Producer<S>, Consumer<E> {
    private val innerState: MutableStateFlow<S> = MutableStateFlow(initialState)
    private val dispatchChannel = Channel<suspend CoroutineScope.(S) -> S>()
    private var job: Job? = null

    init {
        job = scope.launch {
            for (event in dispatchChannel) {
                launch {
                    val newState = event(innerState.value)
                    innerState.emit(newState)
                }
            }
        }
    }

    protected abstract fun createTask(event: E): suspend CoroutineScope.(S) -> S

    final override val state: StateFlow<S> = innerState.asStateFlow()

    final override fun consume(event: E) {
        scope.launch {
            val task = createTask(event)
            dispatchChannel.send(task)
        }
    }

    final override fun onDispose() {
        job?.cancel()
        job = null
    }
}