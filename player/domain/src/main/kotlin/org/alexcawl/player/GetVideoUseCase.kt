package org.alexcawl.player

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import org.alexcawl.common.GetUseCase
import org.alexcawl.scriber.data.configuration.DetectionParametersRepository
import org.alexcawl.scriber.data.video.VideoFileRepository

class GetVideoUseCase(
    private val videoDetectionManagerRepository: VideoDetectionManagerRepository,
    private val videoFileRepository: VideoFileRepository,
    private val detectionParametersPropertyRepository: DetectionParametersRepository
) : GetUseCase<Unit, Sequence<ByteArray>>() {
    override fun execute(input: Unit): Flow<Sequence<ByteArray>> = flow {
        videoFileRepository.get()
            .combine(detectionParametersPropertyRepository.get(), ::Pair)
            .combine(videoDetectionManagerRepository.get(), ::Pair)
            .collect { (data, manager) ->
                emit(
                    when (val file = data.first) {
                        null -> sequenceOf()
                        else -> manager.getMotionDetectedVideo(file, data.second)
                    }
                )
            }
    }
}