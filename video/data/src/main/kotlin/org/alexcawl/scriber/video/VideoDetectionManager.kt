package org.alexcawl.scriber.video

import org.alexcawl.configuration.AccuracyPropertyRepository
import org.alexcawl.configuration.BlurScalePropertyRepository
import org.alexcawl.configuration.ThresholdPropertyRepository

class VideoDetectionManager(
    private val accuracyPropertyRepository: AccuracyPropertyRepository,
    private val blurScalePropertyRepository: BlurScalePropertyRepository,
    private val thresholdPropertyRepository: ThresholdPropertyRepository,
    private val videoFileRepository: VideoFileRepository
) {
}