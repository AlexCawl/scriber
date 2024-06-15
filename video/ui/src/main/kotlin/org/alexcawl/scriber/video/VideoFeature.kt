package org.alexcawl.scriber.video

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import org.alexcawl.scriber.mvi.compose.ComponentScope
import org.alexcawl.scriber.video.di.VideoComponentStore
import org.alexcawl.scriber.video.video.VideoScreen

@Stable
@Composable
fun VideoFeature(modifier: Modifier = Modifier) = ComponentScope(VideoComponentStore.component) {
    VideoScreen(modifier)
}
