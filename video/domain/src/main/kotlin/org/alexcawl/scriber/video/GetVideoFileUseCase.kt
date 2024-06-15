package org.alexcawl.scriber.video

import kotlinx.coroutines.flow.Flow
import org.alexcawl.common.GetUseCase
import org.alexcawl.scriber.data.video.VideoFileRepository
import java.io.File
import javax.inject.Inject

class GetVideoFileUseCase @Inject constructor(
    private val videoFileRepository: VideoFileRepository
) : GetUseCase<Unit, File?>() {
    override fun execute(input: Unit): Flow<File?> = videoFileRepository.get()
}