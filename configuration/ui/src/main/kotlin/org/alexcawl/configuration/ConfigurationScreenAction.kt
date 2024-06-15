package org.alexcawl.configuration

sealed interface ConfigurationScreenAction {
    data class SetAccuracy(val accuracy: Float) : ConfigurationScreenAction

    data class SetBlurScale(val blurScale: Float) : ConfigurationScreenAction

    data class SetThreshold(val threshold: Int) : ConfigurationScreenAction
}