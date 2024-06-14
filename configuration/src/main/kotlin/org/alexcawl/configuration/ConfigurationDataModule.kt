package org.alexcawl.configuration

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import org.alexcawl.scriber.data.configuration.AccuracyPropertyRepository
import org.alexcawl.scriber.data.configuration.BlurScalePropertyRepository
import org.alexcawl.scriber.data.configuration.ThresholdPropertyRepository
import javax.inject.Singleton

@Module
interface ConfigurationDataModule {
    companion object {
        @Provides
        @Singleton
        fun provideAccuracyRepository(dataStore: DataStore<Preferences>) = AccuracyPropertyRepository(dataStore)

        @Provides
        @Singleton
        fun provideBlurScaleRepository(dataStore: DataStore<Preferences>) = BlurScalePropertyRepository(dataStore)

        @Provides
        @Singleton
        fun provideThresholdRepository(dataStore: DataStore<Preferences>) = ThresholdPropertyRepository(dataStore)
    }
}