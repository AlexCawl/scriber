package org.alexcawl.scriber.data.configuration

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import org.alexcawl.common.DetectionParameters
import org.alexcawl.common.PropertyRepository

class DetectionParametersRepository(
    private val accuracyPropertyRepository: AccuracyPropertyRepository,
    private val blurScalePropertyRepository: BlurScalePropertyRepository,
    private val thresholdPropertyRepository: ThresholdPropertyRepository
) : PropertyRepository<DetectionParameters> {
    override fun get(): Flow<DetectionParameters> = flow {
        accuracyPropertyRepository.get()
            .combine(blurScalePropertyRepository.get(), ::Pair)
            .combine(thresholdPropertyRepository.get(), ::Pair).collect {
                emit(DetectionParameters(it.first.first, it.first.second, it.second))
            }
    }

    override suspend fun set(value: DetectionParameters) = Unit
}