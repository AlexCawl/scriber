package org.alexcawl.scriber.video.player

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Store
import org.alexcawl.scriber.mvi.log.Log
import org.alexcawl.scriber.video.GetDetectedVideoUseCase
import org.alexcawl.scriber.video.RestartDetectedVideoUseCase
import org.jetbrains.skia.Image
import javax.inject.Inject

class VideoPlayerScreenStore @Inject constructor(
    scope: CoroutineScope,
    getDetectedVideoUseCase: GetDetectedVideoUseCase,
    private val restartVideoUseCase: RestartDetectedVideoUseCase
) : Store<VideoPlayerScreenState, VideoPlayerScreenAction>(scope, VideoPlayerScreenState.Initial) {
    init {
        task {
            getDetectedVideoUseCase(Unit).collect {
                it.forEach { data ->
                    reduce {
                        when (val image = convertToImage(data)) {
                            null -> VideoPlayerScreenState.Initial
                            else -> VideoPlayerScreenState.State(image)
                        }
                    }
                }

            }
        }
    }

    override fun handle(event: VideoPlayerScreenAction) {
        when (event) {
            VideoPlayerScreenAction.Restart -> task {
                restartVideoUseCase(Unit)
            }
        }
    }

    private fun convertToImage(bytes: ByteArray): ImageBitmap? = try {
        Image.makeFromEncoded(bytes).toComposeImageBitmap()
    } catch (exception: Exception) {
        storeLogger.log(Log("Player", exception))
        null
    }
}