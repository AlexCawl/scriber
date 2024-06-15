package org.alexcawl.scriber.data.theme

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import org.alexcawl.scriber.data.api.DataStorePropertyRepository
import javax.inject.Inject

private val IS_DARK_MODE = booleanPreferencesKey("dark_mode")

class ThemeRepository @Inject constructor(
    dataStore: DataStore<Preferences>
) : DataStorePropertyRepository<Boolean>(dataStore, IS_DARK_MODE, false) {
    suspend fun toggle(): Result<Unit> = runCatching {
        dataStore.edit { preferences ->
            val current = get().first()
            preferences[key] = !current
        }
    }
}