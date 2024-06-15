package org.alexcawl.scriber.video.video

sealed interface VideoScreenAction {
    data object ToggleVideoPlayer : VideoScreenAction

    data object DownloadVideo : VideoScreenAction
}