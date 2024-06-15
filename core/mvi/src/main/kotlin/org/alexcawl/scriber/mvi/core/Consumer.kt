package org.alexcawl.scriber.mvi.core

interface Consumer<E> {
    fun consume(event: E)
}