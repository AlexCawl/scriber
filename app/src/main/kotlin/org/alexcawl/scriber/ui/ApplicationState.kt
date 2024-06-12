package org.alexcawl.scriber.ui

import androidx.compose.runtime.Immutable

@Immutable
sealed interface ApplicationState {
    val isDarkTheme: Boolean

    @Immutable
    data object Loading : ApplicationState {
        override val isDarkTheme: Boolean
            get() = false
    }

    @Immutable
    data class CameraDetectionScreen(override val isDarkTheme: Boolean) : ApplicationState

    @Immutable
    data class VideoDetectionScreen(override val isDarkTheme: Boolean) : ApplicationState
}