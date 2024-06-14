package org.alexcawl.configuration

import kotlinx.coroutines.flow.Flow
import org.alexcawl.common.GetUseCase
import org.alexcawl.scriber.data.configuration.AccuracyPropertyRepository

class GetAccuracyUseCase(
    private val repository: AccuracyPropertyRepository
) : GetUseCase<Unit, Float>() {
    override fun execute(input: Unit): Flow<Float> = repository.get()
}