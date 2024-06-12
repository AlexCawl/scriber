package org.alexcawl.scriber.video

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.DisposableKey

@Module
internal interface VideoUiModule {
    @Binds
    @IntoMap
    @DisposableKey(VideoScreenStore::class)
    fun bindVideoScreenStore(store: VideoScreenStore): Disposable

    companion object {
        @Provides
        fun provideVideoScreenStore(scope: CoroutineScope, useCase: RewriteVideoUseCase): VideoScreenStore = VideoScreenStore(scope, useCase)
    }
}