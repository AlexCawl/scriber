package org.alexcawl.scriber.camera.di

import org.alexcawl.scriber.mvi.core.Disposable

interface CameraComponentDeps {
    val disposableFactory: Disposable.Factory
}