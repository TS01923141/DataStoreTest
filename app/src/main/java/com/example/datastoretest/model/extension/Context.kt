package com.example.datastoretest.model.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastoretest.model.serializer.SettingsSerializer.settingsDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

fun Context.getIntDataFlow(key: String): Flow<Int> {
    val key = intPreferencesKey(key)
    return dataStore.data.map { preferenceKey ->
        // No type safety.
        preferenceKey[key] ?: -1
    }
}

suspend fun Context.setIntData(key: String, value: Int) {
    dataStore.edit { settings ->
        settings[intPreferencesKey(key)] = value
    }
}

fun Context.getProtoIntDataFlow() = settingsDataStore.data.map { settings ->
    //The exampleCounter property is generated from the proto schema.
    settings.exampleCounter
}

suspend fun Context.setProtoIntData(value: Int) {
    settingsDataStore.updateData { currentSettings ->
        currentSettings.toBuilder()
            .setExampleCounter(value)
            .build()
    }
}