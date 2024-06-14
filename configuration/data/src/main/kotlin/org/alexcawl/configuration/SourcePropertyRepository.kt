package org.alexcawl.configuration

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import org.alexcawl.common.PropertyRepository
import org.alexcawl.common.Source

class SourcePropertyRepository : PropertyRepository<Source> {
    private val sourceState: MutableStateFlow<Source> = MutableStateFlow(Source.None)

    override fun get(): Flow<Source> = flow {
        sourceState.collect {
            emit(it)
        }
    }

    override suspend fun set(value: Source) = sourceState.emit(value)
}