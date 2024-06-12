package org.alexcawl.configuration

import org.alexcawl.common.SetUseCase

class SetAccuracyUseCase(
    private val repository: AccuracyPropertyRepository
) : SetUseCase<Float>() {
    override suspend fun invoke(input: Float): Result<Unit> = runCatching {
        repository.set(input)
    }
}