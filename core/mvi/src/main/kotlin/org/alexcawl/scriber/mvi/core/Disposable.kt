package org.alexcawl.scriber.mvi.core

interface Disposable {
    fun onDispose()

    interface Factory {
        fun <M : Disposable> create(modelClass: Class<M>): M
    }
}