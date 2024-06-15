package org.alexcawl.scriber.video

import kotlinx.coroutines.flow.first
import org.alexcawl.common.SetUseCase
import java.io.File
import javax.inject.Inject

class DownloadDetectedVideoUseCase @Inject constructor(
    private val getVideoDetectionManager: GetVideoDetectionManager
) : SetUseCase<File?>() {
    override suspend fun invoke(input: File?): Result<Unit> = runCatching {
        when (val manager = getVideoDetectionManager(Unit).first()) {
            null -> throw IllegalArgumentException("Input file is null")
            else -> manager.loadMotionDetectedVideo()
        }
    }
}