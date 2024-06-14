package org.alexcawl.player

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.ImageBitmap

@Immutable
sealed interface PlayerScreenState {
    val isPlaying: Boolean
    val image: ImageBitmap?

    @Immutable
    data object Initial : PlayerScreenState {
        override val isPlaying: Boolean
            get() = false
        override val image: ImageBitmap?
            get() = null
    }

    @Immutable
    data class State(override val isPlaying: Boolean, override val image: ImageBitmap) : PlayerScreenState
}