package org.alexcawl.scriber.camera.camera

import androidx.compose.foundation.Image
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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

@Composable
private fun CameraScreenContent(
    state: CameraScreenState,
    event: (CameraScreenAction) -> Unit,
    modifier: Modifier = Modifier
) = Scaffold {
    when (val image = state.image) {
        null -> Unit
        else -> Image(
            bitmap = image,
            contentDescription = ""
        )
    }
}