package org.alexcawl.configuration

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import org.alexcawl.scriber.data.DataStorePropertyRepository

private val THRESHOLD = intPreferencesKey("threshold")

class ThresholdPropertyRepository(dataStore: DataStore<Preferences>) : DataStorePropertyRepository<Int>(dataStore, THRESHOLD, 0)
