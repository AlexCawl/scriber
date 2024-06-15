package org.alexcawl.scriber.data.configuration

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.floatPreferencesKey
import org.alexcawl.scriber.data.api.DataStorePropertyRepository
import javax.inject.Inject

private val BLUR_SCALE = floatPreferencesKey("blur_scale")

class BlurScalePropertyRepository @Inject constructor(dataStore: DataStore<Preferences>) : DataStorePropertyRepository<Float>(dataStore, BLUR_SCALE, 0f)