package org.alexcawl.scriber.camera.player

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.ImageBitmap

@Immutable
sealed interface CameraPlayerScreenState {
    val image: ImageBitmap?

    @Immutable
    data object Initial: CameraPlayerScreenState {
        override val image: ImageBitmap?
            get() = null
    }

    @Immutable
    data class State(override val image: ImageBitmap?): CameraPlayerScreenState
}
