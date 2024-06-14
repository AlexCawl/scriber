package org.alexcawl.scriber.video

import kotlinx.coroutines.flow.first
import org.alexcawl.configuration.AccuracyPropertyRepository
import org.alexcawl.configuration.BlurScalePropertyRepository
import org.alexcawl.configuration.ThresholdPropertyRepository
import org.alexcawl.scriber.cv.VideoDetectionManager
import java.io.File

class VideoDetectionService(
    private val accuracyPropertyRepository: AccuracyPropertyRepository,
    private val blurScalePropertyRepository: BlurScalePropertyRepository,
    private val thresholdPropertyRepository: ThresholdPropertyRepository,
    private val videoFileRepository: VideoFileRepository,
    private val detectionManager: VideoDetectionManager
) {
    suspend fun createVideo(): Result<Unit> = runCatching {
        val accuracy: Float = accuracyPropertyRepository.get().first()
        val blurScale: Float = blurScalePropertyRepository.get().first()
        val threshold: Int = thresholdPropertyRepository.get().first()
        val inputVideoFile: File = videoFileRepository.get().first() ?: throw IllegalStateException("Input file not found!")
        val outputVideoFile: File = inputVideoFile.getOutput()
        return runCatching {
            detectionManager.downloadMotionDetectedVideo(inputVideoFile, outputVideoFile)
        }
    }

    suspend fun getVideo(): Sequence<ByteArray> {
        val inputVideoFile: File = videoFileRepository.get().first() ?: throw IllegalStateException("Input file not found!")
        return detectionManager.getMotionDetectedVideo(inputVideoFile)
    }

    private fun File.getOutput(): File {
        val directory: String = this.parent
        val name: String = this.nameWithoutExtension + "log"
        val extension: String = this.extension
        return File(directory, "$name.$extension")
    }
}