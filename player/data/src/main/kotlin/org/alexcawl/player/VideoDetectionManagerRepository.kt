package org.alexcawl.player

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import org.alexcawl.common.PropertyRepository
import org.alexcawl.scriber.cv.VideoDetectionManager

class VideoDetectionManagerRepository : PropertyRepository<VideoDetectionManager> {
    private val managerState = MutableStateFlow(VideoDetectionManager())

    override fun get(): Flow<VideoDetectionManager> = flow {
        managerState.collect {
            emit(it)
        }
    }

    override suspend fun set(value: VideoDetectionManager) = managerState.emit(value)
}