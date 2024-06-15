package org.alexcawl.scriber.cv

import org.alexcawl.common.DetectionParameters
import org.alexcawl.scriber.cv.utils.detectMotion
import org.bytedeco.javacv.Frame

class VideoDetector(parameters: DetectionParameters) : Detector(parameters) {
    override fun detect(frames: Sequence<Frame>): Sequence<Frame> = detectMotion(frames, parameters)
}
