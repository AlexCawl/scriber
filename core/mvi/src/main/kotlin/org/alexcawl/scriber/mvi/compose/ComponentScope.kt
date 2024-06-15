package org.alexcawl.scriber.mvi.compose

import androidx.compose.runtime.*
import org.alexcawl.scriber.mvi.core.DependencyComponent
import org.alexcawl.scriber.mvi.core.Disposable

internal val LocalComponentProvider: ProvidableCompositionLocal<DependencyComponent> =
    staticCompositionLocalOf { error("LocalStoreFactoryProvider not initialized.") }

internal val LocalFactoryProvider: ProvidableCompositionLocal<Disposable.Factory> =
    staticCompositionLocalOf { error("LocalStoreFactoryProvider not initialized.") }

@Stable
@Composable
fun <T : DependencyComponent> ComponentScope(
    component: T,
    content: @Composable () -> Unit
) = CompositionLocalProvider(
    LocalComponentProvider provides component
) {
    CompositionLocalProvider(
        LocalFactoryProvider provides component.factory
    ) {
        content()
    }
}

object ComponentProvider {
    val component: DependencyComponent
        @Stable @Composable get() = LocalComponentProvider.current
}

object FactoryProvider {
    val factory: Disposable.Factory
        @Stable @Composable get() = LocalFactoryProvider.current
}
