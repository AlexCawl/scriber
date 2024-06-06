package org.alexcawl.scriber.video.tuner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.alexcawl.scriber.mvi.compose.StoreScope
import org.alexcawl.scriber.ui.component.inputCard.ValidatedInputCard

private val intValueMapper: (Int) -> String = Int::toString
private val floatValueMapper: (Float) -> String = Float::toString

private val intValueValidator: (String) -> Result<Int> = { runCatching { it.toInt() } }
private val floatValueValidator: (String) -> Result<Float> = { runCatching { it.toFloat() } }


@Composable
fun VideoTunerScreen(
    modifier: Modifier = Modifier
) = StoreScope<VideoTunerScreenState, VideoTunerScreenAction, VideoTunerScreenStore> {
    val state: VideoTunerScreenState by this.state.collectAsState()
    VideoTunerScreenContent(
        state = state,
        event = this::consume,
        modifier = modifier
    )
}

@Composable
private fun VideoTunerScreenContent(
    state: VideoTunerScreenState,
    event: (VideoTunerScreenAction) -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top),
    horizontalAlignment = Alignment.Start
) {
    ValidatedInputCard(
        initialValue = state.accuracy,
        valueMapper = floatValueMapper,
        valueValidator = floatValueValidator,
        onValueSubmit = { event(VideoTunerScreenAction.SetAccuracy(it)) }
    )
    ValidatedInputCard(
        initialValue = state.blurScale,
        valueMapper = floatValueMapper,
        valueValidator = floatValueValidator,
        onValueSubmit = { event(VideoTunerScreenAction.SetBlurScale(it)) }
    )
    ValidatedInputCard(
        initialValue = state.threshold,
        valueMapper = intValueMapper,
        valueValidator = intValueValidator,
        onValueSubmit = { event(VideoTunerScreenAction.SetThreshold(it)) }
    )
}