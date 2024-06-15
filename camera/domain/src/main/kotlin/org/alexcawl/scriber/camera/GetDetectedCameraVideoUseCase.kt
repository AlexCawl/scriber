package org.alexcawl.scriber.camera

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.common.GetUseCase
import javax.inject.Inject

class GetDetectedCameraVideoUseCase @Inject constructor(
    private val getCameraDetectionManager: GetCameraDetectionManager
) : GetUseCase<Unit, Result<ByteArray>>() {
    override fun execute(input: Unit): Flow<Result<ByteArray>> = flow {
        getCameraDetectionManager(Unit).collect { manager ->
            when (manager) {
                null -> emit(Result.failure(IllegalStateException("No camera detected")))
                else -> {
                    manager.getMotionDetectedVideo().forEach {
                        emit(Result.success(it))
                    }
                }
            }
        }
    }
}