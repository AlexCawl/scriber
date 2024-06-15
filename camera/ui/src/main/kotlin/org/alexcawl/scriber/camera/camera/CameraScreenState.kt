package org.alexcawl.scriber.camera.camera

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.ImageBitmap
import org.jetbrains.skia.Bitmap

@Immutable
sealed interface CameraScreenState {
    val image: ImageBitmap?

    data object Initial : CameraScreenState {
        override val image: ImageBitmap?
            get() = null
    }

    data class State(override val image: ImageBitmap) : CameraScreenState
}