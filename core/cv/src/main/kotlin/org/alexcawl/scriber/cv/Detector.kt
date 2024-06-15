package org.alexcawl.scriber.cv

import org.alexcawl.common.DetectionParameters
import org.bytedeco.javacv.Frame

abstract class Detector(protected val parameters: DetectionParameters) {
    abstract fun detect(frames: Sequence<Frame>): Sequence<Frame>
}