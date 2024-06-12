package org.alexcawl.scriber

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import org.alexcawl.scriber.data.dataStorePreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.alexcawl.scriber.data.ThemeRepository
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.DisposableKey
import org.alexcawl.scriber.mvi.core.StoreFactory
import org.alexcawl.scriber.ui.ApplicationStore
import org.alexcawl.scriber.ui.navigation.NavigationStore
import org.alexcawl.scriber.video.VideoModule
import javax.inject.Provider

@Module(includes = [VideoModule::class])
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
        fun provideFactory(creators: @JvmSuppressWildcards Map<Class<out Disposable>, Provider<Disposable>>): Disposable.Factory =
            object : StoreFactory(creators) {}

        @Provides
        fun provideDataStore(): DataStore<Preferences> = dataStorePreferences(
            corruptionHandler = null,
            coroutineScope = CoroutineScope(SupervisorJob()),
            migrations = listOf()
        )

        @Provides
        fun provideNavigationStore(scope: CoroutineScope) = NavigationStore(scope)

        @Provides
        fun provideApplicationStore(scope: CoroutineScope, navigationStore: NavigationStore, themeRepository: ThemeRepository) = ApplicationStore(scope, navigationStore, themeRepository)

        @Provides
        fun provideThemeRepository(dataStore: DataStore<Preferences>) = ThemeRepository(dataStore)
    }
}