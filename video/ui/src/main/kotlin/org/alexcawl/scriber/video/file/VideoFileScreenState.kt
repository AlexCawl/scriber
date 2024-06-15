package org.alexcawl.scriber.video.file

import androidx.compose.runtime.Immutable
import java.io.File

@Immutable
sealed interface VideoFileScreenState {
    val videoFile: File?
    val suffix: String

    @Immutable
    data object Initial : VideoFileScreenState {
        override val videoFile: File?
            get() = null
        override val suffix: String
            get() = ""
    }

    @Immutable
    data class State(override val videoFile: File?, override val suffix: String) : VideoFileScreenState
}