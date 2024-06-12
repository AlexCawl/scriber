package org.alexcawl.scriber.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeRepository(
    private val dataStore: DataStore<Preferences>
) {
    private object PreferencesKeys {
        val IS_DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    fun getTheme(): Flow<Boolean> = dataStore.data.map { preferences ->
        val isDarkTheme = preferences[PreferencesKeys.IS_DARK_MODE] ?: false
        isDarkTheme
    }

    suspend fun setTheme(isDarkTheme: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_DARK_MODE] = isDarkTheme
        }
    }
}