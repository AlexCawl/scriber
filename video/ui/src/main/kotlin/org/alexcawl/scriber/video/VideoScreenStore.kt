package org.alexcawl.scriber.video

import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Store
import java.io.File

class VideoScreenStore(
    scope: CoroutineScope,
    private val rewriteVideo: RewriteVideoUseCase
) : Store<VideoScreenState, VideoScreenAction>(scope, VideoScreenState.Initial) {
    val videoInputPath = File("/home/mick/Downloads/istockphoto-680128580-640_adpp_is.mp4")
    val videoOutputPath = File("output.mp4")

    override fun handle(event: VideoScreenAction) {

    }
}