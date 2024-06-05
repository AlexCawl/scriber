package org.alexcawl.scriber.video

import java.io.File

sealed interface VideoScreenAction {
    data class SelectVideoFile(val file: File) : VideoScreenAction

    data object ActivateDetector : VideoScreenAction

    data object DownloadVideo : VideoScreenAction

    data object DownloadLogs : VideoScreenAction

    data class SetAccuracy(val accuracy: Float) : VideoScreenAction

    data class SetThreshold(val threshold: Int) : VideoScreenAction
}