package org.alexcawl.scriber.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.alexcawl.scriber.mvi.compose.StoreScope
import org.alexcawl.scriber.ui.component.input.ToggleFileInputField

@Composable
fun VideoScreen(
    modifier: Modifier = Modifier,
) = StoreScope<VideoScreenState, VideoScreenAction, VideoScreenStore> {
    val state: VideoScreenState by this.state.collectAsState()
    VideoScreenContent(
        state = state,
        this::consume,
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
        Row {
            Text(text = state.fileName)
            IconButton(
                onClick = { event(VideoScreenAction.ActivateDetector) }
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null
                )
            }
            if (state is VideoScreenState.PostProcessed) {
                IconButton(
                    onClick = { event(VideoScreenAction.DownloadVideo) }
                ) {
                    Icon(
                        imageVector = Icons.Default.VideoFile,
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = { event(VideoScreenAction.DownloadVideo) }
                ) {
                    Icon(
                        imageVector = Icons.Default.FileDownload,
                        contentDescription = null
                    )
                }
            }
        }
    }
) {
    var comparisonWindowOpened: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(it),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        ToggleFileInputField(
            title = "Select file:",
            isSingleSelection = true,
            consume = ::println,
            modifier = Modifier.weight(1f)
                .background(Color.LightGray, shape = MaterialTheme.shapes.large)
                .padding(16.dp)
                .height(64.dp)
        )
        Text("Select params")
        ParameterSelection(
            label = "Accuracy",
            value = state.accuracy.toString(),
            onSelected = {
                event(VideoScreenAction.SetAccuracy(0f))
            })
        ParameterSelection(
            label = "Threshold value",
            value = state.threshold.toString(),
            onSelected = {
                event(VideoScreenAction.SetThreshold(0))
            })
        Button(
            onClick = { comparisonWindowOpened = !comparisonWindowOpened }
        ) {
            Text("Open comparison window")
        }
    }
}

