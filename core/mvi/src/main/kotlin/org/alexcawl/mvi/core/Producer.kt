package org.alexcawl.mvi.core

import kotlinx.coroutines.flow.Flow

interface Producer<S : Any> {
    val state: Flow<S>
}