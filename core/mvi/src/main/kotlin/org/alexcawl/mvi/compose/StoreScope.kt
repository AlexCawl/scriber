package org.alexcawl.mvi.compose

import androidx.compose.runtime.*
import org.alexcawl.mvi.core.Disposable
import org.alexcawl.mvi.core.Store

@Composable
inline fun <S, E, reified M : Store<S, E>> StoreScope(
    crossinline content: @Composable Store<S, E>.() -> Unit
) {
    val disposable: Store<S, E> = StoreFactoryProvider.factory.create(M::class.java)
    val localStoreProvider: ProvidableCompositionLocal<Disposable> = staticCompositionLocalOf { disposable }
    DisposableEffect(M::class.java.toString()) {
        onDispose {
            disposable.onDispose()
        }
    }
    CompositionLocalProvider(
        localStoreProvider provides disposable
    ) {
        with(disposable) {
            content()
        }
    }
}
