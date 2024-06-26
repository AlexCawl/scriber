package org.alexcawl.scriber.video

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.alexcawl.common.GetUseCase
import org.alexcawl.scriber.cv.manager.VideoDetectionManager
import org.alexcawl.scriber.data.configuration.DetectionParametersRepository
import javax.inject.Inject

class GetVideoDetectionManager @Inject constructor(
    private val videoFileRepository: VideoFileRepository,
    private val detectionParametersRepository: DetectionParametersRepository
) : GetUseCase<Unit, VideoDetectionManager?>() {
    override fun execute(input: Unit): Flow<VideoDetectionManager?> =
        videoFileRepository.get()
            .combine(detectionParametersRepository.get(), ::Pair)
            .map { (videoFile, detectionParameters) ->
                when (videoFile) {
                    null -> null
                    else -> VideoDetectionManager(videoFile, detectionParameters)
                }
            }
}