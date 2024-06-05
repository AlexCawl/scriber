package org.alexcawl.scriber.video

import java.io.File

sealed interface VideoScreenState {
    val fileName: String
    val filePath: File?
    val isAnalyzed: Boolean
    val accuracy: Float
    val threshold: Int

    data object Initial: VideoScreenState {
        override val fileName: String
            get() = ""
        override val filePath: File?
            get() = null
        override val isAnalyzed: Boolean
            get() = false
        override val accuracy: Float
            get() = 0f
        override val threshold: Int
            get() = 0
    }

    data class Preprocessed(
        override val fileName: String,
        override val filePath: File,
        override val accuracy: Float,
        override val threshold: Int
    ) : VideoScreenState {
        override val isAnalyzed: Boolean
            get() = false
    }

    data class PostProcessed(
        override val fileName: String,
        override val filePath: File,
        override val accuracy: Float,
        override val threshold: Int
    ) : VideoScreenState {
        override val isAnalyzed: Boolean
            get() = true
    }
}