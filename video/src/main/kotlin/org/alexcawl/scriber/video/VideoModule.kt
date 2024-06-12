package org.alexcawl.scriber.video

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module(includes = [VideoUiModule::class, VideoDomainModule::class, VideoDataModule::class])
interface VideoModule {
    companion object {
        @Provides
        fun provideModuleScope(): CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

        @Provides
        fun provideVideoAnalyzer(): VideoAnalyzer = VideoAnalyzer()

        @Provides
        fun provideRewriteVideoUseCase(scope: CoroutineScope, videoAnalyzer: VideoAnalyzer): RewriteVideoUseCase =
            RewriteVideoUseCase(scope, videoAnalyzer)
    }
}