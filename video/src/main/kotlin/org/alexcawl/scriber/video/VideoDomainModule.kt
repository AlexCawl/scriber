package org.alexcawl.scriber.video

import dagger.Module
import dagger.Provides
import org.alexcawl.scriber.data.video.VideoFileRepository
import javax.inject.Singleton

@Module
interface VideoDomainModule {
    companion object {
        @Provides
        @Singleton
        fun provideGetVideoFileUseCase(repository: VideoFileRepository) = GetVideoFileUseCase(repository)

        @Provides
        @Singleton
        fun provideSetVideoFileUseCase(repository: VideoFileRepository) = SetVideoFileUseCase(repository)

        @Provides
        @Singleton
        fun provideDetectVideoFileUseCase(manager: VideoDetectionService) = DetectVideoUseCase(manager)
    }
}