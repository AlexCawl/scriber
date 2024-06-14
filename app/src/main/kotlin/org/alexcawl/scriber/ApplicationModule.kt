package org.alexcawl.scriber

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import org.alexcawl.scriber.data.api.dataStorePreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.alexcawl.configuration.ConfigurationModule
import org.alexcawl.scriber.cv.VideoDetectionManager
import org.alexcawl.scriber.data.theme.ThemeRepository
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.DisposableKey
import org.alexcawl.scriber.mvi.core.StoreFactory
import org.alexcawl.scriber.ui.ApplicationStore
import org.alexcawl.scriber.ui.navigation.NavigationStore
import org.alexcawl.scriber.video.VideoModule
import javax.inject.Provider
import javax.inject.Singleton

@Module(includes = [VideoModule::class, ConfigurationModule::class])
interface ApplicationModule {
    @Binds
    @IntoMap
    @DisposableKey(NavigationStore::class)
    fun bindNavigationStore(navigationStore: NavigationStore): Disposable

    @Binds
    @IntoMap
    @DisposableKey(ApplicationStore::class)
    fun bindApplicationStore(applicationStore: ApplicationStore): Disposable

    companion object {
        @Provides
        @Singleton
        fun provideFactory(creators: @JvmSuppressWildcards Map<Class<out Disposable>, Provider<Disposable>>): Disposable.Factory =
            object : StoreFactory(creators) {}

        @Provides
        @Singleton
        fun provideDataStore(): DataStore<Preferences> = dataStorePreferences(
            corruptionHandler = null,
            coroutineScope = CoroutineScope(SupervisorJob()),
            migrations = listOf()
        )

        @Provides
        @Singleton
        fun provideNavigationStore(scope: CoroutineScope) = NavigationStore(scope)

        @Provides
        @Singleton
        fun provideApplicationStore(scope: CoroutineScope, navigationStore: NavigationStore, themeRepository: ThemeRepository) = ApplicationStore(scope, navigationStore, themeRepository)

        @Provides
        @Singleton
        fun provideThemeRepository(dataStore: DataStore<Preferences>) = ThemeRepository(dataStore)

        @Provides
        @Singleton
        fun provideVideoDetectionService() = VideoDetectionManager()

        @Provides
        @Singleton
        fun provideModuleScope(): CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    }
}