package org.alexcawl.scriber.video

import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Store

class VideoScreenStore(scope: CoroutineScope) : Store<VideoScreenState, VideoScreenAction>(scope, VideoScreenState.Initial) {
    override fun createTask(event: VideoScreenAction): suspend CoroutineScope.(VideoScreenState) -> VideoScreenState {
        return {
            VideoScreenState.Initial
        }
    }
}