package org.alexcawl.scriber.video

import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Store
import java.io.File

class VideoScreenStore(
    scope: CoroutineScope,
    private val getFileUseCase: GetVideoFileUseCase,
    private val setFileUseCase: SetVideoFileUseCase
) : Store<VideoScreenState, VideoScreenAction>(scope, VideoScreenState.Initial) {
    init {
        task {
            getFileUseCase(Unit).collect { videoFile: File? ->
                reduce {
                    when (videoFile) {
                        null -> VideoScreenState.Initial
                        else -> VideoScreenState.Setup(videoFile)
                    }
                }
            }
        }
    }

    override fun handle(event: VideoScreenAction) = when (event) {
        is VideoScreenAction.SelectVideoFile -> task { setFileUseCase(event.file) }
        VideoScreenAction.DownloadLogs -> TODO()
        VideoScreenAction.DownloadVideo -> TODO()
        VideoScreenAction.ShowDifference -> TODO()
    }
}