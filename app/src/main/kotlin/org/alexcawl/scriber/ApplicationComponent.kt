package org.alexcawl.scriber

import dagger.Component
import org.alexcawl.scriber.mvi.core.DependencyComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : DependencyComponent {
    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }
}