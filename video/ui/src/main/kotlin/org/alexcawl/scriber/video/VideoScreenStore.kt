package org.alexcawl.scriber.video

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import org.alexcawl.scriber.mvi.core.Store
import org.alexcawl.scriber.mvi.log.Log
import java.io.File
import javax.inject.Inject

class VideoScreenStore @Inject constructor(
    scope: CoroutineScope,
    private val getFile: GetVideoFileUseCase,
    private val setFile: SetVideoFileUseCase,
    private val detectVideo: DownloadDetectedVideoUseCase
) : Store<VideoScreenState, VideoScreenAction>(scope, VideoScreenState.Initial) {
    private val videoPlayerOpened: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        task {
            getFile(Unit).collect { videoFile: File? ->
                reduce {
                    when (videoFile) {
                        null -> VideoScreenState.Initial
                        else -> VideoScreenState.State(videoFile, false)
                    }
                }
            }
        }
        task {
            videoPlayerOpened.collect { playerOpened ->
                reduce { oldState ->
                    when (oldState) {
                        VideoScreenState.Initial -> VideoScreenState.Initial
                        is VideoScreenState.State -> oldState.copy(playerOpened = playerOpened)
                    }
                }
            }
        }
    }

    override fun handle(event: VideoScreenAction) = when (event) {
        is VideoScreenAction.SelectVideoFile -> task {
            setFile(event.file)
        }

        VideoScreenAction.DownloadVideo -> task {
            when (val state = state.first()) {
                VideoScreenState.Initial -> Unit
                is VideoScreenState.State -> detectVideo(state.videoFile).also {
                    when (val exception = it.exceptionOrNull()) {
                        null -> Unit
                        else -> storeLogger.log(Log("Video", exception))
                    }
                }
            }
        }

        VideoScreenAction.ToggleVideoPlayer -> task {
            videoPlayerOpened.emit(videoPlayerOpened.first().not())
        }
    }
}