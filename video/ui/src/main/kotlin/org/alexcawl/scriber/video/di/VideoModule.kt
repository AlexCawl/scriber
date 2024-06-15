package org.alexcawl.scriber.video.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.DisposableKey
import org.alexcawl.scriber.video.file.VideoFileScreenStore
import org.alexcawl.scriber.video.player.VideoPlayerScreenStore
import org.alexcawl.scriber.video.video.VideoScreenStore

@Module
interface VideoModule {
    @Binds
    @IntoMap
    @DisposableKey(VideoScreenStore::class)
    fun bindVideoScreenStore(store: VideoScreenStore): Disposable

    @Binds
    @IntoMap
    @DisposableKey(VideoPlayerScreenStore::class)
    fun bindVideoPlayerScreenStore(store: VideoPlayerScreenStore): Disposable

    @Binds
    @IntoMap
    @DisposableKey(VideoFileScreenStore::class)
    fun bindVideoFileScreenStore(store: VideoFileScreenStore): Disposable
}
