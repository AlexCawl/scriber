package org.alexcawl.scriber.ui

import androidx.compose.runtime.Immutable

@Immutable
sealed interface ApplicationState {
    val isDarkTheme: Boolean

    @Immutable
    data class Loading(override val isDarkTheme: Boolean) : ApplicationState

    @Immutable
    data class CameraDetectionScreen(override val isDarkTheme: Boolean) : ApplicationState

    @Immutable
    data class VideoDetectionScreen(override val isDarkTheme: Boolean) : ApplicationState
}