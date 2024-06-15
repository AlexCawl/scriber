package org.alexcawl.scriber.camera.di

import org.alexcawl.common.ComponentStore

object CameraComponentStore : ComponentStore<CameraComponent, CameraComponentDeps> {
    override lateinit var dependencies: CameraComponentDeps

    override val component: CameraComponent
        get() = DaggerCameraComponent.factory().create(dependencies)
}