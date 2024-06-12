package org.alexcawl.configuration

import dagger.Module
import dagger.Provides

@Module
interface ConfigurationDomainModule {
    companion object {
        @Provides
        @ConfigurationScope
        fun provideGetAccuracyUseCase(repository: AccuracyPropertyRepository): GetAccuracyUseCase = GetAccuracyUseCase(repository)

        @Provides
        @ConfigurationScope
        fun provideSetAccuracyUseCase(repository: AccuracyPropertyRepository): SetAccuracyUseCase = SetAccuracyUseCase(repository)

        @Provides
        @ConfigurationScope
        fun provideGetBlurScaleUseCase(repository: BlurScalePropertyRepository): GetBlurScaleUseCase = GetBlurScaleUseCase(repository)

        @Provides
        @ConfigurationScope
        fun provideSetBlurScaleUseCase(repository: BlurScalePropertyRepository): SetBlurScaleUseCase = SetBlurScaleUseCase(repository)

        @Provides
        @ConfigurationScope
        fun provideGetThresholdUseCase(repository: ThresholdPropertyRepository): GetThresholdUseCase = GetThresholdUseCase(repository)

        @Provides
        @ConfigurationScope
        fun provideSetThresholdUseCase(repository: ThresholdPropertyRepository): SetThresholdUseCase = SetThresholdUseCase(repository)
    }
}