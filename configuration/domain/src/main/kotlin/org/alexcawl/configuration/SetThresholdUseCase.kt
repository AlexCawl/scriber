package org.alexcawl.configuration

import org.alexcawl.common.SetUseCase

class SetThresholdUseCase(
    private val repository: ThresholdPropertyRepository
) : SetUseCase<Int>() {
    override suspend fun invoke(input: Int): Result<Unit> = runCatching {
        repository.set(input)
    }
}