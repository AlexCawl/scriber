package org.alexcawl.configuration

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides

@Module
interface ConfigurationDataModule {
    companion object {
        @Provides
        @ConfigurationScope
        fun provideAccuracyRepository(dataStore: DataStore<Preferences>) = AccuracyPropertyRepository(dataStore)

        @Provides
        @ConfigurationScope
        fun provideBlurScaleRepository(dataStore: DataStore<Preferences>) = BlurScalePropertyRepository(dataStore)

        @Provides
        @ConfigurationScope
        fun provideThresholdRepository(dataStore: DataStore<Preferences>) = ThresholdPropertyRepository(dataStore)
    }
}