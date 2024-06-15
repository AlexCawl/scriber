package org.alexcawl.scriber.video

import org.alexcawl.common.SetUseCase
import org.alexcawl.scriber.cv.VideoDetectionManager
import javax.inject.Inject

class RestartDetectedVideoUseCase @Inject constructor(
    private val getVideoDetectionManager: GetVideoDetectionManager
) : SetUseCase<Unit>() {
    override suspend fun invoke(input: Unit): Result<Unit> = runCatching {
        getVideoDetectionManager.refresh()
    }
}