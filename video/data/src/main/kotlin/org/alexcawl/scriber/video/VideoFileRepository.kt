package org.alexcawl.scriber.video

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import org.alexcawl.common.PropertyRepository
import java.io.File

class VideoFileRepository : PropertyRepository<File?> {
    private val fileState = MutableStateFlow<File?>(null)

    override fun get(): Flow<File?> = flow {
        fileState.collect { fileState ->
            emit(fileState)
        }
    }

    override suspend fun set(value: File?) {
        fileState.emit(value)
    }
}