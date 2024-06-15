package org.alexcawl.scriber.video.di

import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.data.configuration.DetectionParametersRepository
import org.alexcawl.scriber.data.configuration.ScribeSuffixPropertyRepository
import org.alexcawl.scriber.mvi.core.Disposable

interface VideoComponentDeps {
    val scope: CoroutineScope
    val detectionParametersRepository: DetectionParametersRepository
    val scribeSuffixPropertyRepository: ScribeSuffixPropertyRepository
    val disposableFactory: Disposable.Factory
}