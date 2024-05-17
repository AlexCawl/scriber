package org.alexcawl.scriber

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import org.alexcawl.mvi.core.Disposable
import org.alexcawl.mvi.core.DisposableKey
import org.alexcawl.scriber.video.DemoStore

@Module
interface VideoModule {
    @Binds
    @IntoMap
    @DisposableKey(DemoStore::class)
    fun bindDemoStore(demoStore: DemoStore): Disposable

    companion object {
        @Provides
        fun provideDemoStore(): DemoStore = DemoStore()
    }
}