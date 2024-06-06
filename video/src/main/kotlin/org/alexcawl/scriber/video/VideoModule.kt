package org.alexcawl.scriber.video

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.DisposableKey
import org.alexcawl.scriber.video.main.VideoScreenStore
import org.alexcawl.scriber.video.tuner.VideoTunerScreenStore

@Module
interface VideoModule {
    @Binds
    @IntoMap
    @DisposableKey(VideoScreenStore::class)
    fun bindVideoScreenStore(store: VideoScreenStore): Disposable

    @Binds
    @IntoMap
    @DisposableKey(VideoTunerScreenStore::class)
    fun bindTunerConfigurationScreenStore(store: VideoTunerScreenStore): Disposable

    companion object {
        @Provides
        fun provideModuleScope(): CoroutineScope = CoroutineScope(Dispatchers.Default)

        @Provides
        fun provideVideoScreenStore(scope: CoroutineScope, useCase: RewriteVideoUseCase): VideoScreenStore = VideoScreenStore(scope, useCase)

        @Provides
        fun provideTunerConfigurationScreenStore(scope: CoroutineScope): VideoTunerScreenStore = VideoTunerScreenStore(scope)

        @Provides
        fun provideVideoAnalyzer(): VideoAnalyzer = VideoAnalyzer()

        @Provides
        fun provideRewriteVideoUseCase(scope: CoroutineScope, videoAnalyzer: VideoAnalyzer): RewriteVideoUseCase =
            RewriteVideoUseCase(scope, videoAnalyzer)
    }
}