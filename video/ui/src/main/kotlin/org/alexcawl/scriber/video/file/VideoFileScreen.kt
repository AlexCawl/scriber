package org.alexcawl.scriber.video.file

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.alexcawl.scriber.mvi.compose.StoreScope
import org.alexcawl.scriber.ui.component.input.ToggleFileInputField
import org.alexcawl.scriber.ui.component.inputCard.ValidatedInputCard

@Composable
fun VideoFileScreen(
    modifier: Modifier = Modifier
) = StoreScope<VideoFileScreenState, VideoFileScreenAction, VideoFileScreenStore> {
    val state: VideoFileScreenState by this.state.collectAsState()
    VideoFileScreenContent(
        state = state,
        event = this::consume,
        modifier = modifier
    )
}

@Composable
private fun VideoFileScreenContent(
    state: VideoFileScreenState,
    event: (VideoFileScreenAction) -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
    horizontalAlignment = Alignment.Start
) {
    Card {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            ToggleFileInputField(
                title = "Select file:",
                isSingleSelection = true,
                consume = { event(VideoFileScreenAction.SelectVideoFile(it)) },
                modifier = Modifier.fillMaxWidth(1f).height(64.dp)
            )
            AnimatedVisibility(state.videoFile != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.Start)
                ) {
                    Button(
                        onClick = { event(VideoFileScreenAction.UnselectVideoFile) },
                        enabled = state.videoFile != null
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                    Text(state.videoFile?.path ?: "Not selected")
                }
            }
        }
    }
    ValidatedInputCard(
        initialValue = state.suffix,
        valueMapper = { it },
        valueValidator = { Result.success(it) },
        onValueSubmit = { event(VideoFileScreenAction.SetSuffix(it)) },
        label = { Text("Detected video suffix") },
        errorLabel = { Text("Invalid value format!") }
    )
}