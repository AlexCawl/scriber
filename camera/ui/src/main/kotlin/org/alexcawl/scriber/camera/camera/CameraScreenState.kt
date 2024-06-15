package org.alexcawl.scriber.camera.camera

import androidx.compose.runtime.Immutable

@Immutable
sealed interface CameraScreenState {
    val isPlayerOpened: Boolean

    @Immutable
    data object Initial : CameraScreenState {
        override val isPlayerOpened: Boolean
            get() = false
    }

    @Immutable
    data class State(override val isPlayerOpened: Boolean) : CameraScreenState
}