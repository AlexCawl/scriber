package org.alexcawl.mvi.core

import javax.inject.Provider

abstract class StoreFactory(
    private val creators: @JvmSuppressWildcards Map<Class<out Disposable>, Provider<Disposable>>
) : Disposable.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <M : Disposable> create(modelClass: Class<M>): M {
        var creator: Provider<out Disposable>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        return when (creator) {
            null -> error("Unknown model class: $modelClass")
            else -> try {
                creator.get() as M
            } catch (e: Exception) {
                error(e)
            }
        }
    }
}