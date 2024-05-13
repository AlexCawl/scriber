package org.alexcawl.scriber.video

import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class VideoScope

@Module
interface VideoModule

interface VideoDependencies

@VideoScope
@Component(modules = [VideoModule::class], dependencies = [VideoDependencies::class])
interface ProfileComponent {
    @Component.Factory
    interface Factory {
        fun produce(dependencies: VideoDependencies): ProfileComponent
    }
}