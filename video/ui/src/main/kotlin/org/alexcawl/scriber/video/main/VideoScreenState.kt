package org.alexcawl.scriber.video.main

import androidx.compose.runtime.Immutable
import java.io.File

@Immutable
sealed interface VideoScreenState {
    val videoFile: File?

    @Immutable
    data object Initial : VideoScreenState {
        override val videoFile: File?
            get() = null
    }

    @Immutable
    data class Setup(override val videoFile: File) : VideoScreenState
}