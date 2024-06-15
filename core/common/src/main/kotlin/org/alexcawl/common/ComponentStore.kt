package org.alexcawl.common

interface ComponentStore<C, D> {
    var dependencies: D

    val component: C
}
