package org.alexcawl.scriber.video

import org.alexcawl.common.SetUseCase
import org.alexcawl.scriber.data.video.VideoFileRepository
import java.io.File
import javax.inject.Inject

class SetVideoFileUseCase @Inject constructor(
    private val videoFileRepository: VideoFileRepository
) : SetUseCase<File?>() {
    override suspend fun invoke(input: File?): Result<Unit> = runCatching {
        if (input == null) throw IllegalArgumentException("Input file is null!")
        if (!input.valid()) throw IllegalArgumentException("Input file does not exist!")
        if (!input.isVideoFile()) throw IllegalArgumentException("Input file extension is not supported: ${input.extension}")
        videoFileRepository.set(input)
    }

    private fun File.valid(): Boolean = exists() && isFile

    private fun File.isVideoFile(): Boolean = extension == "mp4"
}