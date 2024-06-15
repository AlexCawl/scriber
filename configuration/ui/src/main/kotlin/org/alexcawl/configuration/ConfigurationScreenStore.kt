package org.alexcawl.configuration

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.combine
import org.alexcawl.scriber.mvi.core.Store

class ConfigurationScreenStore(
    scope: CoroutineScope,
    private val getAccuracy: GetAccuracyUseCase,
    private val setAccuracy: SetAccuracyUseCase,
    private val getBlurScale: GetBlurScaleUseCase,
    private val setBlurScale: SetBlurScaleUseCase,
    private val getThreshold: GetThresholdUseCase,
    private val setThreshold: SetThresholdUseCase,
) : Store<ConfigurationScreenState, ConfigurationScreenAction>(scope, ConfigurationScreenState.Initial) {
    init {
        task {
            getAccuracy(Unit)
                .combine(getBlurScale(Unit), ::Pair)
                .combine(getThreshold(Unit), ::Pair)
                .collect {
                    val accuracy: Float = it.first.first
                    val blurScale: Float = it.first.second
                    val threshold: Int = it.second
                    reduce { _ ->
                        ConfigurationScreenState.Setup(
                            accuracy = accuracy,
                            blurScale = blurScale,
                            threshold = threshold
                        )
                    }
                }
        }
    }

    override fun handle(event: ConfigurationScreenAction) {
        when (event) {
            is ConfigurationScreenAction.SetAccuracy -> configureAccuracy(event.accuracy)
            is ConfigurationScreenAction.SetBlurScale -> configureBlurScale(event.blurScale)
            is ConfigurationScreenAction.SetThreshold -> configureThreshold(event.threshold)
        }
    }

    private fun configureAccuracy(accuracy: Float) = task { setAccuracy(accuracy) }

    private fun configureBlurScale(blurScale: Float) = task { setBlurScale(blurScale) }

    private fun configureThreshold(threshold: Int) = task { setThreshold(threshold) }
}