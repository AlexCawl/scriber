package org.alexcawl.common

import kotlinx.coroutines.flow.Flow

interface PropertyRepository<T> {
    fun get(): Flow<T>

    suspend fun set(value: T)
}