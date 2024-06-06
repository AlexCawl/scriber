package org.alexcawl.scriber.video.tuner

sealed interface VideoTunerScreenAction {
    data class SetAccuracy(val accuracy: Float) : VideoTunerScreenAction

    data class SetBlurScale(val blurScale: Float) : VideoTunerScreenAction

    data class SetThreshold(val threshold: Int) : VideoTunerScreenAction
}