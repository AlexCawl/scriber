package org.alexcawl.configuration

import kotlinx.coroutines.flow.Flow
import org.alexcawl.common.GetUseCase

class GetThresholdUseCase(private val repository: ThresholdPropertyRepository) : GetUseCase<Unit, Int>() {
    override fun execute(input: Unit): Flow<Int> = repository.get()
}
