package org.alexcawl.mvi.core

import kotlinx.coroutines.flow.StateFlow

interface Producer<S> {
    val state: StateFlow<S>
}