package org.alexcawl.scriber.data.api

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.common.PropertyRepository

abstract class DataStorePropertyRepository<T>(
    protected val dataStore: DataStore<Preferences>,
    protected val key: Preferences.Key<T>,
    private val defaultValue: T
) : PropertyRepository<T> {
    override fun get(): Flow<T> = dataStore.data.map { preferences ->
        preferences[key] ?: defaultValue
    }

    override suspend fun set(value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}