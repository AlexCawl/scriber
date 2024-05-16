package org.alexcawl.mvi.core

interface Disposable {
    fun onDispose()

    interface Factory {
        fun <M : Disposable> create(modelClass: Class<M>): M
    }
}