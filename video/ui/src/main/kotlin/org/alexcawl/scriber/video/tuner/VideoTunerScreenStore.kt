package org.alexcawl.scriber.video.tuner

import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Store

class VideoTunerScreenStore(
    scope: CoroutineScope
) : Store<VideoTunerScreenState, VideoTunerScreenAction>(scope, VideoTunerScreenState.Initial) {
    override fun handle(event: VideoTunerScreenAction) {
        when (event) {
            is VideoTunerScreenAction.SetAccuracy -> setAccuracy(event.accuracy)
            is VideoTunerScreenAction.SetBlurScale -> setBlurScale(event.blurScale)
            is VideoTunerScreenAction.SetThreshold -> setThreshold(event.threshold)
        }
    }

    private fun setAccuracy(accuracy: Float) = task {
        reduce { previous ->
            VideoTunerScreenState.Setup(
                accuracy = accuracy,
                blurScale = previous.blurScale,
                threshold = previous.threshold
            )
        }
    }

    private fun setBlurScale(blurScale: Float) = task {
        reduce { previous ->
            VideoTunerScreenState.Setup(
                accuracy = previous.accuracy,
                blurScale = blurScale,
                threshold = previous.threshold
            )
        }
    }

    private fun setThreshold(threshold: Int) = task {
        reduce { previous ->
            VideoTunerScreenState.Setup(
                accuracy = previous.accuracy,
                blurScale = previous.blurScale,
                threshold = threshold
            )
        }
    }
}