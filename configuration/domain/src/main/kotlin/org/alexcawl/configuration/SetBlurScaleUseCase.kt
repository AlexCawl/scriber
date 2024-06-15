package org.alexcawl.configuration

import org.alexcawl.common.SetUseCase
import org.alexcawl.scriber.data.configuration.BlurScalePropertyRepository

class SetBlurScaleUseCase(
    private val repository: BlurScalePropertyRepository
) : SetUseCase<Float>() {
    override suspend fun invoke(input: Float): Result<Unit> = runCatching {
        repository.set(input)
    }
}