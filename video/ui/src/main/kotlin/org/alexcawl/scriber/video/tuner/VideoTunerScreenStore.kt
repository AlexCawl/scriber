package org.alexcawl.scriber.video.tuner

import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Store

class VideoTunerScreenStore(
    scope: CoroutineScope,
) : Store<VideoTunerScreenState, VideoTunerScreenAction>(scope, VideoTunerScreenState.Initial) {
    override fun createTask(event: VideoTunerScreenAction): suspend CoroutineScope.(VideoTunerScreenState) -> VideoTunerScreenState {
        TODO("Not yet implemented")
    }
}