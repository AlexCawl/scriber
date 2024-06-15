package org.alexcawl.scriber.data.configuration

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import org.alexcawl.scriber.data.api.DataStorePropertyRepository
import javax.inject.Inject

private val THRESHOLD = intPreferencesKey("threshold")

class ThresholdPropertyRepository @Inject constructor(dataStore: DataStore<Preferences>) :
    DataStorePropertyRepository<Int>(dataStore, THRESHOLD, 0)