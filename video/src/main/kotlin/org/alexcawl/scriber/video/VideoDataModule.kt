package org.alexcawl.scriber.video

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface VideoDataModule {
    companion object {
        @Provides
        @Singleton
        fun provideVideoFileRepository() = VideoFileRepository()
    }
}