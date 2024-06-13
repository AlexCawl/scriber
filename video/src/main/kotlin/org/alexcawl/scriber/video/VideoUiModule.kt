package org.alexcawl.scriber.video

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.DisposableKey
import javax.inject.Singleton

@Module
internal interface VideoUiModule {
    @Binds
    @IntoMap
    @DisposableKey(VideoScreenStore::class)
    fun bindVideoScreenStore(store: VideoScreenStore): Disposable

    companion object {
        @Provides
        @Singleton
        fun provideVideoScreenStore(
            scope: CoroutineScope,
            getFileUseCase: GetVideoFileUseCase,
            setFileUseCase: SetVideoFileUseCase,
            detectVideoUseCase: DetectVideoUseCase
        ): VideoScreenStore = VideoScreenStore(scope, getFileUseCase, setFileUseCase, detectVideoUseCase)
    }
}