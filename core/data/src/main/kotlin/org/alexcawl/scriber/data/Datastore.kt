package org.alexcawl.scriber.data

import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.io.File

internal fun createDataStore(
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>? = null,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    migrations: List<DataMigration<Preferences>>,
    context: Any? = null,
    path: (context: Any?) -> File,
) = PreferenceDataStoreFactory.create(
    corruptionHandler = corruptionHandler,
    scope = coroutineScope,
    migrations = migrations,
    produceFile = { path(context) }
)

fun dataStorePreferences(
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>?,
    coroutineScope: CoroutineScope,
    migrations: List<DataMigration<Preferences>>
): DataStore<Preferences> = createDataStore(
    corruptionHandler = corruptionHandler,
    coroutineScope = coroutineScope,
    migrations = migrations,
    path = { File("scriber.preferences_pb") }
)