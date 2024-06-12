package org.alexcawl.scriber.video

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module(includes = [VideoUiModule::class, VideoDomainModule::class, VideoDataModule::class])
interface VideoModule {
    companion object {
        @Provides
        @Singleton
        fun provideModuleScope(): CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

        @Provides
        @Singleton
        fun provideVideoAnalyzer(): VideoDetectionService = VideoDetectionService()
    }
}