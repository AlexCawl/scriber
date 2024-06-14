package org.alexcawl.player

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface PlayerDataModule {
    companion object {
        @Provides
        @Singleton
        fun provideVideoDetectionManagerRepository() = VideoDetectionManagerRepository()
    }
}