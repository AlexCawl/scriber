package org.alexcawl.scriber

import dagger.Module
import dagger.Provides
import org.alexcawl.mvi.core.Disposable
import org.alexcawl.mvi.core.StoreFactory
import javax.inject.Provider

@Module(includes = [VideoModule::class])
interface ApplicationModule {
    companion object {
        @Provides
        fun provideFactory(creators: @JvmSuppressWildcards Map<Class<out Disposable>, Provider<Disposable>>): Disposable.Factory =
            object : StoreFactory(creators) {}
    }
}