package org.alexcawl.scriber.video.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.alexcawl.scriber.mvi.compose.StoreScope

@Composable
fun PlayerScreen(modifier: Modifier) = StoreScope<VideoPlayerScreenState, VideoPlayerScreenAction, VideoPlayerScreenStore> {
    val state: VideoPlayerScreenState by this.state.collectAsState()
    PlayerScreenContent(
        state = state,
        event = this::consume,
        modifier = modifier
    )
}

@Composable
private fun PlayerScreenContent(
    state: VideoPlayerScreenState,
    event: (VideoPlayerScreenAction) -> Unit,
    modifier: Modifier = Modifier
) = Column(modifier) {
    when (val image = state.image) {
        null -> Unit
        else -> Image(bitmap = image, contentDescription = null, modifier = Modifier.fillMaxWidth())
    }
}