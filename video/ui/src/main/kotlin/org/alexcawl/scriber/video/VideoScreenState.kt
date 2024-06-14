package org.alexcawl.scriber.video

import androidx.compose.runtime.Immutable
import java.io.File

@Immutable
sealed interface VideoScreenState {
    val videoFile: File?
    val playerOpened: Boolean

    @Immutable
    data object Initial : VideoScreenState {
        override val videoFile: File?
            get() = null
        override val playerOpened: Boolean
            get() = false
    }

    @Immutable
    data class State(override val videoFile: File, override val playerOpened: Boolean) : VideoScreenState
}