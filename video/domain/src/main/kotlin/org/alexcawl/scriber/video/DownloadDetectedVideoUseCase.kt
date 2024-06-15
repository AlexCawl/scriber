package org.alexcawl.scriber.video

import kotlinx.coroutines.flow.first
import org.alexcawl.common.SetUseCase
import org.alexcawl.scriber.data.configuration.ScribeSuffixPropertyRepository
import java.io.File
import javax.inject.Inject

class DownloadDetectedVideoUseCase @Inject constructor(
    private val getVideoDetectionManager: GetVideoDetectionManager,
    private val scribeSuffixPropertyRepository: ScribeSuffixPropertyRepository
) : SetUseCase<File?>() {
    override suspend fun invoke(input: File?): Result<Unit> = runCatching {
        when (val manager = getVideoDetectionManager(Unit).first()) {
            null -> throw IllegalArgumentException("Input file is null")
            else -> {
                val suffix: String = scribeSuffixPropertyRepository.get().first()
                manager.loadMotionDetectedVideo(suffix)
            }
        }
    }
}