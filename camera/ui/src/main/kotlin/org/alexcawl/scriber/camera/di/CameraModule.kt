package org.alexcawl.scriber.camera.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.scriber.camera.camera.CameraScreenStore
import org.alexcawl.scriber.camera.player.CameraPlayerScreenStore
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.DisposableKey

@Module
interface CameraModule {
    @Binds
    @IntoMap
    @DisposableKey(CameraScreenStore::class)
    fun bindCameraScreenStore(cameraScreenStore: CameraScreenStore): Disposable

    @Binds
    @IntoMap
    @DisposableKey(CameraPlayerScreenStore::class)
    fun bindCameraPlayerScreenStore(cameraPlayerScreenStore: CameraPlayerScreenStore): Disposable
}