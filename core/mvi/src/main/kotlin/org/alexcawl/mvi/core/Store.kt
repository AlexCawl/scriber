package org.alexcawl.mvi.core

abstract class Store<S : Any, E : Any> : Producer<S>, Consumer<E> {
    
}