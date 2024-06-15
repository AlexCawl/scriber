package org.alexcawl.scriber.camera.player

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.camera.GetDetectedCameraVideoUseCase
import org.alexcawl.scriber.mvi.core.Store
import org.alexcawl.scriber.mvi.log.Log
import org.jetbrains.skia.Image
import javax.inject.Inject

class CameraPlayerScreenStore @Inject constructor(
    scope: CoroutineScope,
    private val getCameraVideo: GetDetectedCameraVideoUseCase
) : Store<CameraPlayerScreenState, CameraPlayerScreenAction>(scope, CameraPlayerScreenState.Initial) {
    init {
        task {
            getCameraVideo(Unit).collect { videoFrame ->
                reduce {
                    when (val byteArray = videoFrame.getOrNull()) {
                        null -> CameraPlayerScreenState.Initial
                        else -> when (val image = convertToImage(byteArray)) {
                            null -> CameraPlayerScreenState.Initial
                            else -> CameraPlayerScreenState.State(image)
                        }
                    }
                }
            }
        }
    }

    override fun handle(event: CameraPlayerScreenAction) = Unit

    private fun convertToImage(bytes: ByteArray): ImageBitmap? = try {
        Image.makeFromEncoded(bytes).toComposeImageBitmap()
    } catch (exception: Exception) {
        storeLogger.log(Log("Player", exception))
        null
    }
}