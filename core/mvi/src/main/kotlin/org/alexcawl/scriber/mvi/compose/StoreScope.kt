package org.alexcawl.scriber.mvi.compose

import androidx.compose.runtime.*
import org.alexcawl.scriber.mvi.core.Disposable
import org.alexcawl.scriber.mvi.core.Store

@Stable
@Composable
inline fun <S, E, reified M : Store<S, E>> StoreScope(
    crossinline content: @Composable M.() -> Unit
) {
    val store: M by rememberStore()
    val localStoreProvider: ProvidableCompositionLocal<Disposable> = staticCompositionLocalOf { store }
    DisposableEffect(store::class.java.toString()) {
        onDispose {
            store.onDispose()
        }
    }
    CompositionLocalProvider(
        localStoreProvider provides store
    ) {
        with(store) {
            content()
        }
    }
}

@Stable
@Composable
inline fun <S, E, reified M : Store<S, E>> rememberStore(): State<M> {
    val store: M = StoreFactoryProvider.factory.create(M::class.java)
    return remember { mutableStateOf(store) }
}
