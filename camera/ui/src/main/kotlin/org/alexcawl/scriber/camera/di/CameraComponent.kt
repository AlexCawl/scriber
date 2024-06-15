package org.alexcawl.scriber.camera.di

import dagger.Component
import org.alexcawl.scriber.mvi.core.DependencyComponent

@CameraScope
@Component(modules = [CameraModule::class], dependencies = [CameraComponentDeps::class])
interface CameraComponent : DependencyComponent {
    @Component.Factory
    interface Factory {
        fun create(deps: CameraComponentDeps): CameraComponent
    }
}