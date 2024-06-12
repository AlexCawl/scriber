package org.alexcawl.configuration

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.DisposableKey
import javax.inject.Singleton

@Module
interface ConfigurationUiModule {
    @Binds
    @IntoMap
    @DisposableKey(ConfigurationScreenStore::class)
    fun bindConfigurationScreenStore(store: ConfigurationScreenStore): Disposable

    companion object {
        @Provides
        @Singleton
        fun provideConfigurationScreenStore(
            scope: CoroutineScope,
            getAccuracy: GetAccuracyUseCase,
            setAccuracy: SetAccuracyUseCase,
            getBlurScale: GetBlurScaleUseCase,
            setBlurScale: SetBlurScaleUseCase,
            getThreshold: GetThresholdUseCase,
            setThreshold: SetThresholdUseCase,
        ): ConfigurationScreenStore = ConfigurationScreenStore(
            scope,
            getAccuracy,
            setAccuracy,
            getBlurScale,
            setBlurScale,
            getThreshold,
            setThreshold
        )
    }
}