package org.alexcawl.scriber.video

import kotlinx.coroutines.flow.Flow
import org.alexcawl.common.GetUseCase
import java.io.File

class GetVideoFileUseCase(
    private val videoFileRepository: VideoFileRepository
) : GetUseCase<Unit, File?>() {
    override fun execute(input: Unit): Flow<File?> = videoFileRepository.get()
}