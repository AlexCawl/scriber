package org.alexcawl.player

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.common.Source
import org.alexcawl.configuration.SourcePropertyRepository
import org.alexcawl.scriber.video.VideoDetectionService

class PlayerService(
    private val sourcePropertyRepository: SourcePropertyRepository,
    private val videoDetectionService: VideoDetectionService
) {
    fun get(): Flow<Sequence<ByteArray>> = flow {
        sourcePropertyRepository.get().collect { source: Source ->
            emit(
                when (source) {
                    Source.None -> sequence {  }
                    is Source.FileSource -> videoDetectionService.getVideo()
                }
            )
        }
    }
}