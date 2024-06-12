package org.alexcawl.scriber.video

import java.io.File

sealed interface VideoScreenAction {
    data class SelectVideoFile(val file: File) : VideoScreenAction

    data object ShowDifference : VideoScreenAction

    data object DownloadVideo : VideoScreenAction

    data object DownloadLogs : VideoScreenAction
}