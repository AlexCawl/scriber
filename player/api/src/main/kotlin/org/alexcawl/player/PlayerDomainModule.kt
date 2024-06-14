package org.alexcawl.player

import dagger.Module
import dagger.Provides
import org.alexcawl.scriber.data.configuration.DetectionParametersRepository
import org.alexcawl.scriber.data.video.VideoFileRepository
import javax.inject.Singleton

@Module
interface PlayerDomainModule {
    companion object {
        @Provides
        @Singleton
        fun provideGetVideoUseCase(
            videoDetectionManagerRepository: VideoDetectionManagerRepository,
            videoFileRepository: VideoFileRepository,
            detectionParametersRepository: DetectionParametersRepository
        ) = GetVideoUseCase(videoDetectionManagerRepository, videoFileRepository, detectionParametersRepository)

        @Provides
        @Singleton
        fun provideRestartVideoUseCase(
            videoDetectionManagerRepository: VideoDetectionManagerRepository,
        ) = RestartVideoUseCase(videoDetectionManagerRepository)
    }
}