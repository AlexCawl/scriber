package org.alexcawl.mvi.core

interface Consumer<E> {
    fun consume(event: E)
}