package org.alexcawl.configuration

import dagger.Module
import dagger.Provides
import org.alexcawl.scriber.data.configuration.AccuracyPropertyRepository
import org.alexcawl.scriber.data.configuration.BlurScalePropertyRepository
import org.alexcawl.scriber.data.configuration.ThresholdPropertyRepository
import javax.inject.Singleton

@Module
interface ConfigurationDomainModule {
    companion object {
        @Provides
        @Singleton
        fun provideGetAccuracyUseCase(repository: AccuracyPropertyRepository): GetAccuracyUseCase = GetAccuracyUseCase(repository)

        @Provides
        @Singleton
        fun provideSetAccuracyUseCase(repository: AccuracyPropertyRepository): SetAccuracyUseCase = SetAccuracyUseCase(repository)

        @Provides
        @Singleton
        fun provideGetBlurScaleUseCase(repository: BlurScalePropertyRepository): GetBlurScaleUseCase = GetBlurScaleUseCase(repository)

        @Provides
        @Singleton
        fun provideSetBlurScaleUseCase(repository: BlurScalePropertyRepository): SetBlurScaleUseCase = SetBlurScaleUseCase(repository)

        @Provides
        @Singleton
        fun provideGetThresholdUseCase(repository: ThresholdPropertyRepository): GetThresholdUseCase = GetThresholdUseCase(repository)

        @Provides
        @Singleton
        fun provideSetThresholdUseCase(repository: ThresholdPropertyRepository): SetThresholdUseCase = SetThresholdUseCase(repository)
    }
}