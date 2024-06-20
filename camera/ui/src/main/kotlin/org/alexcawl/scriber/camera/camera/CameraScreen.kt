package org.alexcawl.scriber.camera.camera

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import org.alexcawl.configuration.ConfigurationScreen
import org.alexcawl.scriber.camera.player.CameraPlayerScreen
import org.alexcawl.scriber.mvi.compose.StoreScope

@Composable
fun CameraScreen(modifier: Modifier = Modifier) = StoreScope<CameraScreenState, CameraScreenAction, CameraScreenStore> {
    val state: CameraScreenState by this.state.collectAsState()
    CameraScreenContent(
        state = state,
        event = this::consume,
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CameraScreenContent(
    state: CameraScreenState,
    event: (CameraScreenAction) -> Unit,
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    topBar = {
        Row(
            modifier = Modifier.fillMaxWidth().height(64.dp).background(MaterialTheme.colors.surface).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            TooltipArea(
                tooltip = {
                    Text(
                        text = "Show motion detected webcam video",
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.background(MaterialTheme.colors.background, MaterialTheme.shapes.small).padding(4.dp)
                    )
                }
            ) {
                IconButton(
                    onClick = { event(CameraScreenAction.ToggleCameraPlayer) }
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null
                    )
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
        item(key = "configuration_screen") {
            ConfigurationScreen(modifier = Modifier.fillMaxWidth())
        }
    }

    if (state.isPlayerOpened) {
        Window(onCloseRequest = { event(CameraScreenAction.ToggleCameraPlayer) }, title = "Camera player") {
            CameraPlayerScreen(modifier = Modifier.fillMaxSize())
        }
    }
}