package org.alexcawl.scriber.camera.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
fun CameraPlayerScreen(modifier: Modifier = Modifier) = StoreScope<CameraPlayerScreenState, CameraPlayerScreenAction, CameraPlayerScreenStore> {
    val state: CameraPlayerScreenState by this.state.collectAsState()
    CameraPlayerScreenContent(
        state = state,
        event = this::consume,
        modifier = modifier
    )
}

@Composable
private fun CameraPlayerScreenContent(
    state: CameraPlayerScreenState,
    event: (CameraPlayerScreenAction) -> Unit,
    modifier: Modifier = Modifier
) = Scaffold(modifier = modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            CameraPlayerScreenState.Initial -> CircularProgressIndicator(modifier = Modifier.size(64.dp))
            is CameraPlayerScreenState.State -> when (val image = state.image) {
                null -> Unit
                else -> Image(
                    bitmap = image,
                    contentDescription = ""
                )
            }
        }
    }
}