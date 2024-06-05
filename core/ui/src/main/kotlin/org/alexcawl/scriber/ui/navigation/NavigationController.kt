package org.alexcawl.scriber.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

class NavigationController internal constructor(startDestination: String) {
    private val _current: MutableState<String> = mutableStateOf(startDestination)
    private val _backstack: LinkedHashSet<String> = linkedSetOf()

    val currentDestination: State<String>
        get() = _current

    fun navigate(route: String): Unit = when (route) {
        _current.value -> Unit
        else -> {
            _backstack.remove(route)
            _backstack.add(_current.value)
            _current.value = route
        }
    }

    fun navigateBack() {
        if (_backstack.isNotEmpty()) {
            val previousDestination = _backstack.last()
            _backstack.remove(previousDestination)
            _current.value = previousDestination
        }
    }
}

@Composable
fun rememberNavController(startDestination: String): MutableState<NavigationController> = rememberSaveable {
    mutableStateOf(NavigationController(startDestination))
}