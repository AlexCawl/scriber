package org.alexcawl.scriber.camera

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.alexcawl.scriber.camera.camera.CameraScreen
import org.alexcawl.scriber.camera.di.CameraComponentStore
import org.alexcawl.scriber.mvi.compose.ComponentScope

@Composable
fun CameraScreenFeature(modifier: Modifier = Modifier) = ComponentScope(CameraComponentStore.component) {
    CameraScreen(modifier)
}
