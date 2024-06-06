package org.alexcawl.scriber.video.tuner

import androidx.compose.runtime.Immutable

@Immutable
sealed interface VideoTunerScreenState {
    val accuracy: Float
    val blurScale: Float
    val threshold: Int

    @Immutable
    data object Initial : VideoTunerScreenState {
        override val accuracy: Float
            get() = 0f
        override val blurScale: Float
            get() = 0f
        override val threshold: Int
            get() = 1
    }

    @Immutable
    data class Setup(
        override val accuracy: Float,
        override val blurScale: Float,
        override val threshold: Int
    ) : VideoTunerScreenState
}