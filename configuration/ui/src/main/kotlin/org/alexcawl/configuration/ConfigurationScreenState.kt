package org.alexcawl.configuration

import androidx.compose.runtime.Immutable

@Immutable
sealed interface ConfigurationScreenState {
    val accuracy: Float
    val blurScale: Float
    val threshold: Int

    @Immutable
    data object Initial : ConfigurationScreenState {
        override val accuracy: Float
            get() = 0f
        override val blurScale: Float
            get() = 0f
        override val threshold: Int
            get() = 0
    }

    @Immutable
    data class Setup(
        override val accuracy: Float,
        override val blurScale: Float,
        override val threshold: Int
    ) : ConfigurationScreenState
}