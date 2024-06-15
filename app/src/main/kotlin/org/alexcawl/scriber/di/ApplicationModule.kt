package org.alexcawl.scriber.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.alexcawl.configuration.ConfigurationModule
import org.alexcawl.scriber.camera.di.CameraModule
import org.alexcawl.scriber.data.api.dataStorePreferences
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.DisposableKey
import org.alexcawl.scriber.mvi.core.StoreFactory
import org.alexcawl.scriber.ui.ApplicationStore
import org.alexcawl.scriber.ui.navigation.NavigationStore
import org.alexcawl.scriber.video.di.VideoModule
import javax.inject.Singleton

@Module(includes = [VideoModule::class, ConfigurationModule::class, CameraModule::class])
interface ApplicationModule {
    @Binds
    @IntoMap
    @DisposableKey(NavigationStore::class)
    fun bindNavigationStore(navigationStore: NavigationStore): Disposable

    @Binds
    @IntoMap
    @DisposableKey(ApplicationStore::class)
    fun bindApplicationStore(applicationStore: ApplicationStore): Disposable

    @Binds
    fun bindDisposableFactory(storeFactory: StoreFactory): Disposable.Factory

    companion object {
        @Provides
        @Singleton
        fun provideDataStore(): DataStore<Preferences> = dataStorePreferences(
            corruptionHandler = null,
            coroutineScope = CoroutineScope(SupervisorJob()),
            migrations = listOf()
        )

        @Provides
        @Singleton
        fun provideCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    }
}