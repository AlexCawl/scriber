package org.alexcawl.scriber.video

class VideoMotionDetectionService {
    private var accuracy: Float = 0f
        set(value) {
            field = value.coerceIn(0f, 1f)
        }

    private var blurScale: Float = 0f
        set(value) {
            field = value.coerceIn(0f, 1f)
        }

    private var threshold: Int = 1
        set(value) {
            field = value
        }


}
