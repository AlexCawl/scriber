package org.alexcawl.scriber.camera.camera

import androidx.compose.runtime.Immutable

@Immutable
sealed interface CameraScreenState {
    val isCameraOpened: Boolean

    data object Initial : CameraScreenState {
        override val isCameraOpened: Boolean
            get() = false
    }

    data class State(override val isCameraOpened: Boolean) : CameraScreenState
}