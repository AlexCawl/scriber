package org.alexcawl.configuration

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.floatPreferencesKey
import org.alexcawl.scriber.data.DataStorePropertyRepository

private val ACCURACY = floatPreferencesKey("accuracy")

class AccuracyPropertyRepository(dataStore: DataStore<Preferences>) : DataStorePropertyRepository<Float>(dataStore, ACCURACY, 0f)
