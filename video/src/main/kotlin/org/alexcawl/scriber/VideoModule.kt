package org.alexcawl.scriber

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.alexcawl.mvi.core.Disposable
import org.alexcawl.mvi.core.DisposableKey
import org.alexcawl.scriber.video.VideoScreenStore

@Module
interface VideoModule {
    @Binds
    @IntoMap
    @DisposableKey(VideoScreenStore::class)
    fun bindDemoStore(store: VideoScreenStore): Disposable

    companion object {
        @Provides
        fun provideDemoStore(): VideoScreenStore = VideoScreenStore(CoroutineScope(Dispatchers.Default))
    }
}