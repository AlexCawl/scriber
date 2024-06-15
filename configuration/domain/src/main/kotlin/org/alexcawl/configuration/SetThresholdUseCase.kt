package org.alexcawl.configuration

import org.alexcawl.common.SetUseCase
import org.alexcawl.scriber.data.configuration.ThresholdPropertyRepository

class SetThresholdUseCase(
    private val repository: ThresholdPropertyRepository
) : SetUseCase<Int>() {
    override suspend fun invoke(input: Int): Result<Unit> = runCatching {
        repository.set(input)
    }
}