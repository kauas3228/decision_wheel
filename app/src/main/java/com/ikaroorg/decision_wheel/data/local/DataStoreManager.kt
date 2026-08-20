package com.ikaroorg.decision_wheel.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {
    companion object {
        val LANGUAGE = stringPreferencesKey("language")
        val IS_INITIALIZED = booleanPreferencesKey("is_initialized")
    }

    val language: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[LANGUAGE] ?: "English"
    }

    val isInitialized: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_INITIALIZED] ?: false
    }

    suspend fun saveLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE] = language
        }
    }

    suspend fun saveIsInitialized(isSelected: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_INITIALIZED] = isSelected
        }
    }
}