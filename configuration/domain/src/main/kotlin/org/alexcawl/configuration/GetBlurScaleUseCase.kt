package org.alexcawl.configuration

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.common.GetUseCase

class GetBlurScaleUseCase(
    private val repository: BlurScalePropertyRepository
) : GetUseCase<Unit, Float>() {
    override fun execute(input: Unit): Flow<Float> = repository.get()
}
