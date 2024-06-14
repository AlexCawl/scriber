package org.alexcawl.scriber.video

import java.io.File

sealed interface VideoScreenAction {
    data class SelectVideoFile(val file: File) : VideoScreenAction

    data object ToggleVideoPlayer : VideoScreenAction

    data object DownloadVideo : VideoScreenAction
}