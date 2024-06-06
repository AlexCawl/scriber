package org.alexcawl.scriber.video.main

import androidx.compose.runtime.Immutable
import java.io.File

@Immutable
sealed interface VideoScreenState {
    @Immutable
    data object Initial : VideoScreenState

    @Immutable
    data class Setup(val videoFile: File) : VideoScreenState
}