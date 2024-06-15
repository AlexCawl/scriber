package org.alexcawl.scriber.video.file

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.combine
import org.alexcawl.scriber.mvi.core.Store
import org.alexcawl.scriber.video.GetVideoFileUseCase
import org.alexcawl.scriber.video.GetVideoSuffixUseCase
import org.alexcawl.scriber.video.SetVideoFileUseCase
import org.alexcawl.scriber.video.SetVideoSuffixUseCase
import javax.inject.Inject

class VideoFileScreenStore @Inject constructor(
    scope: CoroutineScope,
    private val getFile: GetVideoFileUseCase,
    private val setFile: SetVideoFileUseCase,
    private val getSuffix: GetVideoSuffixUseCase,
    private val setSuffix: SetVideoSuffixUseCase
) : Store<VideoFileScreenState, VideoFileScreenAction>(scope, VideoFileScreenState.Initial) {
    init {
        task {
            getFile(Unit)
                .combine(getSuffix(Unit), ::Pair)
                .collect { (file, suffix) ->
                    reduce {
                        VideoFileScreenState.State(file, suffix)
                    }
                }
        }
    }

    override fun handle(event: VideoFileScreenAction) = when (event) {
        is VideoFileScreenAction.SelectVideoFile -> task {
            setFile(event.file)
        }

        is VideoFileScreenAction.SetSuffix -> task {
            setSuffix(event.fileSuffix)
        }

        VideoFileScreenAction.UnselectVideoFile -> task {
            setFile(null)
        }
    }
}