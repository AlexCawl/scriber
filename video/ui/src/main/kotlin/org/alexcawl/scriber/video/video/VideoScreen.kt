package org.alexcawl.scriber.video.video

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import org.alexcawl.configuration.ConfigurationScreen
import org.alexcawl.scriber.video.player.PlayerScreen
import org.alexcawl.scriber.mvi.compose.StoreScope
import org.alexcawl.scriber.video.file.VideoFileScreen

@Composable
fun VideoScreen(
    modifier: Modifier = Modifier
) = StoreScope<VideoScreenState, VideoScreenAction, VideoScreenStore> {
    val state: VideoScreenState by this.state.collectAsState()
    VideoScreenContent(
        state = state,
        event = this::consume,
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun VideoScreenContent(
    state: VideoScreenState,
    event: (VideoScreenAction) -> Unit,
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    topBar = {
        Row(
            modifier = Modifier.fillMaxWidth().height(64.dp).background(MaterialTheme.colors.surface).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            when (state) {
                VideoScreenState.Initial -> Text(text = "File not selected!")
                is VideoScreenState.State -> {
                    Text(text = state.videoFile.name)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
                    ) {
                        TooltipArea(
                            tooltip = {
                                Text(
                                    text = "Show motion detected video",
                                    color = MaterialTheme.colors.onBackground,
                                    modifier = Modifier.background(MaterialTheme.colors.background, MaterialTheme.shapes.small).padding(4.dp)
                                )
                            }
                        ) {
                            IconButton(
                                onClick = { event(VideoScreenAction.ToggleVideoPlayer) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null
                                )
                            }
                        }

                        TooltipArea(
                            tooltip = {
                                Text(
                                    text = "Download motion detected video",
                                    color = MaterialTheme.colors.onBackground,
                                    modifier = Modifier.background(MaterialTheme.colors.background, MaterialTheme.shapes.small).padding(4.dp)
                                )
                            }
                        ) {
                            IconButton(
                                onClick = { event(VideoScreenAction.DownloadVideo) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Download,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }
) { padding: PaddingValues ->
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(16.dp)
    ) {
        item(key = "video_file_screen") {
            VideoFileScreen(modifier = Modifier.fillMaxWidth())
        }
        item(key = "configuration_screen") {
            ConfigurationScreen(modifier = Modifier.fillMaxWidth())
        }
    }

    if (state.playerOpened) {
        Window(onCloseRequest = { event(VideoScreenAction.ToggleVideoPlayer) }, title = "Video player") {
            PlayerScreen(modifier = Modifier.fillMaxSize())
        }
    }
}
