package org.alexcawl.scriber.video

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import org.alexcawl.scriber.mvi.core.Store
import java.io.File

class VideoScreenStore(
    scope: CoroutineScope,
    private val getFile: GetVideoFileUseCase,
    private val setFile: SetVideoFileUseCase,
    private val detectVideo: DetectVideoUseCase
) : Store<VideoScreenState, VideoScreenAction>(scope, VideoScreenState.Initial) {
    init {
        task {
            getFile(Unit).collect { videoFile: File? ->
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
        is VideoScreenAction.SelectVideoFile -> task { setFile(event.file) }
        VideoScreenAction.DownloadLogs -> TODO()
        VideoScreenAction.DownloadVideo -> handleDownloadVideo()
        VideoScreenAction.ShowDifference -> TODO()
    }

    private fun handleDownloadVideo() = task {
        when (val state = state.first()) {
            VideoScreenState.Initial -> Unit
            is VideoScreenState.Setup -> detectVideo(state.videoFile).also {
                println("VideoScreenStore::handleDownloadVideo: $it")
            }
        }
    }
}