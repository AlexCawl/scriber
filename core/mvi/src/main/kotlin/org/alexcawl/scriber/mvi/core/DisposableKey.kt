package org.alexcawl.scriber.mvi.core

import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class DisposableKey(val value: KClass<out Disposable>)