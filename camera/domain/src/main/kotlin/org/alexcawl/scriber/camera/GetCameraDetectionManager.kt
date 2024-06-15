package org.alexcawl.scriber.camera

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.common.GetUseCase
import org.alexcawl.scriber.cv.manager.CameraDetectionManager
import org.alexcawl.scriber.data.configuration.DetectionParametersRepository
import javax.inject.Inject

class GetCameraDetectionManager @Inject constructor(
    private val detectionParametersRepository: DetectionParametersRepository
) : GetUseCase<Unit, CameraDetectionManager?>() {
    override fun execute(input: Unit): Flow<CameraDetectionManager?> = detectionParametersRepository.get()
        .map(::CameraDetectionManager)
}