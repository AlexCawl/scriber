package org.alexcawl.scriber.video.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
) = Scaffold(modifier = modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            VideoPlayerScreenState.Initial -> CircularProgressIndicator(modifier = Modifier.size(64.dp))
            is VideoPlayerScreenState.State -> Image(
                bitmap = state.image,
                contentDescription = null
            )
        }
    }
}