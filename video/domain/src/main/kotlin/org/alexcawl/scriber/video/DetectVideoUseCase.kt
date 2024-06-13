package org.alexcawl.scriber.video

import org.alexcawl.common.SetUseCase
import java.io.File

class DetectVideoUseCase(
    private val videoDetectionManager: VideoDetectionManager
) : SetUseCase<File?>() {
    override suspend fun invoke(input: File?): Result<Unit> = runCatching {
        val inputVideoFile = input ?: throw IllegalArgumentException("Input file is null")
        when (inputVideoFile.exists() && inputVideoFile.isFile) {
            true -> when (inputVideoFile.extension == "mp4") {
                true -> return videoDetectionManager.createVideo(File("scribed.mp4"))
                false -> throw IllegalArgumentException("Input file extension is not supported: ${inputVideoFile.extension}")
            }

            false -> throw IllegalArgumentException("Input file doesn't exist: $inputVideoFile")
        }
    }
}