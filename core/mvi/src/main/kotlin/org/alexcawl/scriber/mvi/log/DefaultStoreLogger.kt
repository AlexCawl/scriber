package org.alexcawl.scriber.mvi.log

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DefaultStoreLogger(scope: CoroutineScope) : StoreLogger(3, scope) {
    init {
        scope.launch {
            logs.collect {
                println("Handle: $it")
            }
        }
    }
}
