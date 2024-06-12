package org.alexcawl.scriber.mvi.core

interface DependencyComponent {
    val factory: Disposable.Factory
}