package org.alexcawl.scriber.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class PropertyRepository<T>(
    protected val dataStore: DataStore<Preferences>,
    protected val key: Preferences.Key<T>,
    private val defaultValue: T
) {
    fun get(): Flow<T> = dataStore.data.map { preferences ->
        preferences[key] ?: defaultValue
    }

    suspend fun set(value: T): Result<Unit> = runCatching {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}