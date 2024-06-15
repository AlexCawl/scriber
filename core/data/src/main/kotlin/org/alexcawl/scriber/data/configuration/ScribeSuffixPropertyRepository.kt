package org.alexcawl.scriber.data.configuration

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import org.alexcawl.scriber.data.api.DataStorePropertyRepository
import javax.inject.Inject

private val SCRIBE_SUFFIX = stringPreferencesKey("scribe_suffix")

class ScribeSuffixPropertyRepository @Inject constructor(dataStore: DataStore<Preferences>) :
    DataStorePropertyRepository<String>(dataStore, SCRIBE_SUFFIX, "_scribe")