package org.alexcawl.player

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Store
import org.alexcawl.scriber.mvi.log.Log
import org.jetbrains.skia.Image

class PlayerScreenStore(
    scope: CoroutineScope,
    getVideoUseCase: GetVideoUseCase,
    private val restartVideoUseCase: RestartVideoUseCase
) : Store<PlayerScreenState, PlayerScreenAction>(scope, PlayerScreenState.Initial) {
    init {
        task {
            getVideoUseCase(Unit).collect {
                it.forEach { data ->
                    reduce {
                        when (val image = convertToImage(data)) {
                            null -> PlayerScreenState.Initial
                            else -> PlayerScreenState.State(image)
                        }
                    }
                }

            }
        }
    }

    override fun handle(event: PlayerScreenAction) {
        when (event) {
            PlayerScreenAction.Restart -> task {
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