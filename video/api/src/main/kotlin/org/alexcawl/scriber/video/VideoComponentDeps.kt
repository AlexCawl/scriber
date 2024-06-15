package org.alexcawl.scriber.video

import org.alexcawl.scriber.data.configuration.DetectionParametersRepository
import org.alexcawl.scriber.mvi.core.Disposable

interface VideoComponentDeps {
    val detectionParametersRepository: DetectionParametersRepository
    val disposableFactory: Disposable.Factory
}