package org.alexcawl.scriber.video

import kotlinx.coroutines.flow.first
import org.alexcawl.configuration.AccuracyPropertyRepository
import org.alexcawl.configuration.BlurScalePropertyRepository
import org.alexcawl.configuration.ThresholdPropertyRepository
import org.alexcawl.scriber.cv.VideoDetectionService
import java.io.File

private val pathOut = File("/home/mick/Downloads/output.mp4")

class VideoDetectionManager(
    private val accuracyPropertyRepository: AccuracyPropertyRepository,
    private val blurScalePropertyRepository: BlurScalePropertyRepository,
    private val thresholdPropertyRepository: ThresholdPropertyRepository,
    private val videoFileRepository: VideoFileRepository,
    private val detectionService: VideoDetectionService
) {
    suspend fun createVideo(outputVideoFile: File): Result<Unit> = runCatching {
        val accuracy: Float = accuracyPropertyRepository.get().first()
        val blurScale: Float = blurScalePropertyRepository.get().first()
        val threshold: Int = thresholdPropertyRepository.get().first()
        val inputVideoFile: File = videoFileRepository.get().first() ?: throw IllegalStateException("Input file not found!")
        return detectionService.downloadMotionDetectedVideo(inputVideoFile, pathOut)
    }
}