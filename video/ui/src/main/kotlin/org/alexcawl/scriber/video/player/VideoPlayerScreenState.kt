package org.alexcawl.scriber.video.player

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.ImageBitmap

@Immutable
sealed interface VideoPlayerScreenState {
    val image: ImageBitmap?

    @Immutable
    data object Initial : VideoPlayerScreenState {
        override val image: ImageBitmap?
            get() = null
    }

    @Immutable
    data class State(override val image: ImageBitmap) : VideoPlayerScreenState
}