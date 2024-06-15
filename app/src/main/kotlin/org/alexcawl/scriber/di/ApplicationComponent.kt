package org.alexcawl.scriber.di

import dagger.Component
import org.alexcawl.scriber.camera.di.CameraComponentDeps
import org.alexcawl.scriber.mvi.core.DependencyComponent
import org.alexcawl.scriber.video.di.VideoComponentDeps
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : DependencyComponent, VideoComponentDeps, CameraComponentDeps {
    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }
}