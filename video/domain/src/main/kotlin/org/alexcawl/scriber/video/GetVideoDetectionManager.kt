package org.alexcawl.scriber.video

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.alexcawl.common.GetUseCase
import org.alexcawl.scriber.cv.VideoDetectionManager
import org.alexcawl.scriber.data.configuration.DetectionParametersRepository
import org.alexcawl.scriber.data.video.VideoFileRepository
import java.util.*
import javax.inject.Inject

class GetVideoDetectionManager @Inject constructor(
    private val videoFileRepository: VideoFileRepository,
    private val detectionParametersRepository: DetectionParametersRepository
) : GetUseCase<Unit, VideoDetectionManager?>() {
    private val key: MutableStateFlow<UUID> = MutableStateFlow(UUID.randomUUID())

    override fun execute(input: Unit): Flow<VideoDetectionManager?> =
        key.combine(videoFileRepository.get()) { _, videoFile -> videoFile }
            .combine(detectionParametersRepository.get(), ::Pair)
            .map { (videoFile, detectionParameters) ->
                when (videoFile) {
                    null -> null
                    else -> VideoDetectionManager(videoFile, detectionParameters)
                }
            }

    suspend fun refresh() = key.emit(UUID.randomUUID())
}