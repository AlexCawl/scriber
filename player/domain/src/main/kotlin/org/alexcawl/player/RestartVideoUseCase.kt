package org.alexcawl.player

import org.alexcawl.common.SetUseCase
import org.alexcawl.scriber.cv.VideoDetectionManager

class RestartVideoUseCase(
    private val videoDetectionManagerRepository: VideoDetectionManagerRepository
) : SetUseCase<Unit>() {
    override suspend fun invoke(input: Unit): Result<Unit> = runCatching {
        videoDetectionManagerRepository.set(VideoDetectionManager())
    }
}