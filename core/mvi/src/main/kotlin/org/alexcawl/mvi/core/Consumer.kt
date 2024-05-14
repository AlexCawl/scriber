package org.alexcawl.mvi.core

interface Consumer<E : Any> {
    fun consume(event: E)
}