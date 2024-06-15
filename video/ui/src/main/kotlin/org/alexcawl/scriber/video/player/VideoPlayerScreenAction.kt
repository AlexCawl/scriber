package org.alexcawl.scriber.video.player

sealed interface VideoPlayerScreenAction {
    data object Restart : VideoPlayerScreenAction
}