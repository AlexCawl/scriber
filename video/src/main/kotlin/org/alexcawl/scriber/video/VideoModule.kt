package org.alexcawl.scriber.video

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module(includes = [VideoUiModule::class, VideoDomainModule::class, VideoDataModule::class])
interface VideoModule {
    companion object {
        @Provides
        fun provideModuleScope(): CoroutineScope = CoroutineScope(Dispatchers.Default)

        @Provides
        fun provideVideoAnalyzer(): VideoAnalyzer = VideoAnalyzer()

        @Provides
        fun provideRewriteVideoUseCase(scope: CoroutineScope, videoAnalyzer: VideoAnalyzer): RewriteVideoUseCase =
            RewriteVideoUseCase(scope, videoAnalyzer)
    }
}