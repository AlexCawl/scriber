package org.alexcawl.scriber.video

import dagger.Module
import dagger.Provides
import org.alexcawl.scriber.cv.VideoDetectionManager
import org.alexcawl.scriber.data.configuration.AccuracyPropertyRepository
import org.alexcawl.scriber.data.configuration.BlurScalePropertyRepository
import org.alexcawl.scriber.data.configuration.ThresholdPropertyRepository
import org.alexcawl.scriber.data.video.VideoFileRepository
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
            videoDetectionService: VideoDetectionManager
        ) = VideoDetectionService(
            accuracyPropertyRepository,
            blurScalePropertyRepository,
            thresholdPropertyRepository,
            videoFileRepository,
            videoDetectionService
        )
    }
}