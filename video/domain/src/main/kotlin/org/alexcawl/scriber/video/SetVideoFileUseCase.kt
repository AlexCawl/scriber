package org.alexcawl.scriber.video

import org.alexcawl.common.SetUseCase
import java.io.File

class SetVideoFileUseCase(
    private val videoFileRepository: VideoFileRepository
) : SetUseCase<File?>() {
    override suspend fun invoke(input: File?): Result<Unit> = runCatching {
        videoFileRepository.set(input)
    }
}