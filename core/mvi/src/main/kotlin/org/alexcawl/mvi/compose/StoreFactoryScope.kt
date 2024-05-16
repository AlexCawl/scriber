package org.alexcawl.mvi.compose

import androidx.compose.runtime.*
import org.alexcawl.mvi.core.Disposable

internal val LocalStoreFactoryProvider: ProvidableCompositionLocal<Disposable.Factory> =
    staticCompositionLocalOf { error("LocalStoreFactoryProvider not initialized.") }

@Stable
@Composable
fun <F : Disposable.Factory> StoreFactoryScope(
    factory: F,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalStoreFactoryProvider provides factory
    ) {
        content()
    }
}

object StoreFactoryProvider {
    val factory: Disposable.Factory
        @Stable @Composable get() = LocalStoreFactoryProvider.current
}