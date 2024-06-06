package org.alexcawl.scriber.video.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.alexcawl.scriber.mvi.compose.StoreScope
import org.alexcawl.scriber.ui.component.input.ToggleFileInputField
import org.alexcawl.scriber.video.tuner.VideoTunerScreen

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

@Composable
internal fun VideoScreenContent(
    state: VideoScreenState,
    event: (VideoScreenAction) -> Unit,
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    topBar = {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = state.videoFile?.name ?: "File not selected!")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                IconButton(
                    onClick = { event(VideoScreenAction.ShowDifference) }
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = { event(VideoScreenAction.DownloadVideo) }
                ) {
                    Icon(
                        imageVector = Icons.Default.VideoFile,
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = { event(VideoScreenAction.DownloadLogs) }
                ) {
                    Icon(
                        imageVector = Icons.Default.FileDownload,
                        contentDescription = null
                    )
                }
            }
        }
    }
) { padding: PaddingValues ->
    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        ToggleFileInputField(
            title = "Select file:",
            isSingleSelection = true,
            consume = { event(VideoScreenAction.SelectVideoFile(it)) },
            modifier = Modifier
                .background(Color.LightGray, shape = MaterialTheme.shapes.large)
                .padding(16.dp)
                .height(64.dp)
        )
        VideoTunerScreen(modifier = Modifier.fillMaxWidth())
    }
}

