package org.alexcawl.scriber

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dataStorePreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.StoreFactory
import org.alexcawl.scriber.video.VideoModule
import javax.inject.Provider

@Module(includes = [VideoModule::class])
interface ApplicationModule {
    companion object {
        @Provides
        fun provideFactory(creators: @JvmSuppressWildcards Map<Class<out Disposable>, Provider<Disposable>>): Disposable.Factory =
            object : StoreFactory(creators) {}

        @Provides
        fun provideDataStore(): DataStore<Preferences> = dataStorePreferences(
            corruptionHandler = null,
            coroutineScope = CoroutineScope(SupervisorJob()),
            migrations = listOf()
        )
    }
}