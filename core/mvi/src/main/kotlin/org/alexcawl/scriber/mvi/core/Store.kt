package org.alexcawl.scriber.mvi.core

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.alexcawl.scriber.mvi.log.DefaultStoreLogger
import org.alexcawl.scriber.mvi.log.Log
import org.alexcawl.scriber.mvi.log.StoreLogger
import kotlin.coroutines.CoroutineContext

abstract class Store<S, E>(
    scope: CoroutineScope,
    initialState: S,
    protected val storeLogger: StoreLogger = DefaultStoreLogger(scope)
) : Disposable, Producer<S>, Consumer<E> {
    private val innerState: MutableStateFlow<S> = MutableStateFlow(initialState)
    private val innerEvent = MutableSharedFlow<E>(0, Int.MAX_VALUE)
    private val dispatchChannel = Channel<suspend StoreContext<S>.() -> Unit>(Channel.UNLIMITED)
    private var job: Job? = null
    private val context = StoreContext(innerState::update)

    init {
        job = scope.launch(
            CoroutineExceptionHandler { coroutineContext: CoroutineContext, throwable: Throwable ->
                storeLogger.log(Log("Exception", throwable))
            }
        ) {
            launch {
                for (task in dispatchChannel) {
                    launch {
                        task(context)
                    }
                }
            }
            launch {
                innerEvent.collect(::handle)
            }
        }
    }

    final override val state: StateFlow<S> = innerState.asStateFlow()

    final override fun consume(event: E) {
        innerEvent.tryEmit(event)
    }

    override fun onDispose() {
        job?.cancel()
        job = null
    }

    protected abstract fun handle(event: E)

    protected fun task(task: suspend StoreContext<S>.() -> Unit) {
        dispatchChannel.trySend(task)
    }
}