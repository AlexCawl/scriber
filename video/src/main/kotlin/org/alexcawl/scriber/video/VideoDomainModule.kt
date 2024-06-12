package org.alexcawl.scriber.video

import dagger.Module
import dagger.Provides
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
    }
}