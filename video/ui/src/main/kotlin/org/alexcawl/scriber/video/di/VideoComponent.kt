package org.alexcawl.scriber.video.di

import dagger.Component
import org.alexcawl.scriber.mvi.core.DependencyComponent

@VideoScope
@Component(modules = [VideoModule::class], dependencies = [VideoComponentDeps::class])
interface VideoComponent : DependencyComponent {
    @Component.Factory
    interface Factory {
        fun create(dependencies: VideoComponentDeps): VideoComponent
    }
}
