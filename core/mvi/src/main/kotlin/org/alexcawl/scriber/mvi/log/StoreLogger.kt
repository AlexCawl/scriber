package org.alexcawl.scriber.mvi.log

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class StoreLogger(size: Int, private val scope: CoroutineScope) {
    private val innerLogs: MutableSharedFlow<Log> = MutableSharedFlow(size)

    val logs: SharedFlow<Log> = innerLogs.asSharedFlow()

    fun log(log: Log) {
        scope.launch {
            innerLogs.emit(log)
        }
    }
}