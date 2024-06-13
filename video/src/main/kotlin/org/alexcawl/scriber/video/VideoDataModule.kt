package org.alexcawl.scriber.video

import dagger.Module
import dagger.Provides
import org.alexcawl.configuration.AccuracyPropertyRepository
import org.alexcawl.configuration.BlurScalePropertyRepository
import org.alexcawl.configuration.ThresholdPropertyRepository
import org.alexcawl.scriber.cv.VideoDetectionService
import javax.inject.Singleton

@Module
interface VideoDataModule {
    companion object {
        @Provides
        @Singleton
        fun provideVideoFileRepository() = VideoFileRepository()

        @Provides
        @Singleton
        fun provideVideoDetectionManager(
            accuracyPropertyRepository: AccuracyPropertyRepository,
            blurScalePropertyRepository: BlurScalePropertyRepository,
            thresholdPropertyRepository: ThresholdPropertyRepository,
            videoFileRepository: VideoFileRepository,
            videoDetectionService: VideoDetectionService
        ) = VideoDetectionManager(
            accuracyPropertyRepository,
            blurScalePropertyRepository,
            thresholdPropertyRepository,
            videoFileRepository,
            videoDetectionService
        )
    }
}