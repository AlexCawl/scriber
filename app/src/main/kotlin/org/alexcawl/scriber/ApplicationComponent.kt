package org.alexcawl.scriber

import dagger.Component
import org.alexcawl.scriber.mvi.core.Disposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    val storeFactory: Disposable.Factory

    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }
}