package org.alexcawl.mvi.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import org.alexcawl.mvi.core.Disposable

internal val LocalStoreFactoryProvider: ProvidableCompositionLocal<Disposable.Factory> =
    staticCompositionLocalOf { error("LocalStoreFactoryProvider not initialized.") }

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
        @Composable get() = LocalStoreFactoryProvider.current
}