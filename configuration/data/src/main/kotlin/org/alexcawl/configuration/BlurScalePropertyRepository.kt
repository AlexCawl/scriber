package org.alexcawl.configuration

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.floatPreferencesKey
import org.alexcawl.scriber.data.DataStorePropertyRepository

private val BLUR_SCALE = floatPreferencesKey("blur_scale")

class BlurScalePropertyRepository(dataStore: DataStore<Preferences>) : DataStorePropertyRepository<Float>(dataStore, BLUR_SCALE, 0f)
