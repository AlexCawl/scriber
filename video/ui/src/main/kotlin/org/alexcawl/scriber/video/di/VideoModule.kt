package org.alexcawl.scriber.video.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.DisposableKey
import org.alexcawl.scriber.video.file.VideoFileScreenStore
import org.alexcawl.scriber.video.player.VideoPlayerScreenStore
import org.alexcawl.scriber.video.video.VideoScreenStore
import javax.inject.Singleton

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
