package org.alexcawl.scriber.video.di

import org.alexcawl.common.ComponentStore

object VideoComponentStore : ComponentStore<VideoComponent, VideoComponentDeps> {
    override lateinit var dependencies: VideoComponentDeps

    override val component: VideoComponent
        get() = DaggerVideoComponent.factory().create(dependencies)
}
