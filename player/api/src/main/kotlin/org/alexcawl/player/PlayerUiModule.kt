package org.alexcawl.player

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.DisposableKey
import javax.inject.Singleton

@Module
interface PlayerUiModule {
    @Binds
    @IntoMap
    @DisposableKey(PlayerScreenStore::class)
    fun bindVideoScreenStore(store: PlayerScreenStore): Disposable

    companion object {
        @Provides
        @Singleton
        fun providePlayerScreenStore(
            scope: CoroutineScope,
            getVideoUseCase: GetVideoUseCase,
            restartVideoUseCase: RestartVideoUseCase
        ) = PlayerScreenStore(scope, getVideoUseCase, restartVideoUseCase)
    }
}