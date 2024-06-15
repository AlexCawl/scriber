package org.alexcawl.scriber.camera.camera

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.camera.GetDetectedCameraVideoUseCase
import org.alexcawl.scriber.mvi.core.Store
import org.alexcawl.scriber.mvi.log.Log
import org.jetbrains.skia.Image
import javax.inject.Inject

class CameraScreenStore @Inject constructor(
    scope: CoroutineScope,
    private val getVideo: GetDetectedCameraVideoUseCase
) : Store<CameraScreenState, CameraScreenAction>(scope, CameraScreenState.Initial) {
    init {
        task {
            getVideo(Unit).collect { videoFrame ->
                reduce {
                    when (val byteArray = videoFrame.getOrNull()) {
                        null -> CameraScreenState.Initial
                        else -> when (val image = convertToImage(byteArray)) {
                            null -> CameraScreenState.Initial
                            else -> CameraScreenState.State(image)
                        }
                    }
                }
            }
        }
    }

    override fun handle(event: CameraScreenAction) {
        TODO("Not yet implemented")
    }

    private fun convertToImage(bytes: ByteArray): ImageBitmap? = try {
        Image.makeFromEncoded(bytes).toComposeImageBitmap()
    } catch (exception: Exception) {
        storeLogger.log(Log("Player", exception))
        null
    }
}