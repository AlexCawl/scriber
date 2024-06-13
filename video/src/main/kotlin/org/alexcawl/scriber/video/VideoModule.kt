package org.alexcawl.scriber.video

import dagger.Module

@Module(includes = [VideoUiModule::class, VideoDomainModule::class, VideoDataModule::class])
interface VideoModule
