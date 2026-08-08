package com.ikaroorg.decision_wheel.data.Local

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ikaroorg.decision_wheel.data.Model.Option
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("localOptions")

class DataStoreManager(private val context: Context) {
    companion object {
        val OPTIONS = stringPreferencesKey("options")
    }

    val options: Flow<List<Option>> = context.dataStore.data.map { preferences ->
        val optionsJson = preferences[OPTIONS] ?: return@map emptyList()

        try {
            Json.decodeFromString<List<Option>>(optionsJson)
        } catch(e: Exception){
            Log.d("option error:", e.message.toString())
            emptyList()
        }
    }

    // suspend functions
    suspend fun saveOptions(options: List<Option>) {
        context.dataStore.edit { preferences ->
            preferences[OPTIONS] = Json.encodeToString(options)
        }
    }

}