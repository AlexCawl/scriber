package org.alexcawl.scriber.video

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.DisposableKey

@Module
interface VideoModule {
    @Binds
    @IntoMap
    @DisposableKey(VideoScreenStore::class)
    fun bindDemoStore(store: VideoScreenStore): Disposable

    companion object {
        @Provides
        fun provideModuleScope(): CoroutineScope = CoroutineScope(Dispatchers.Default)

        @Provides
        fun provideDemoStore(scope: CoroutineScope, useCase: RewriteVideoUseCase): VideoScreenStore = VideoScreenStore(scope, useCase)

        @Provides
        fun provideVideoAnalyzer(): VideoAnalyzer = VideoAnalyzer()

        @Provides
        fun provideRewriteVideoUseCase(scope: CoroutineScope, videoAnalyzer: VideoAnalyzer): RewriteVideoUseCase =
            RewriteVideoUseCase(scope, videoAnalyzer)
    }
}