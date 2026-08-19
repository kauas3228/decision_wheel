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
        val IS_SELECTED_LANGUAGE = booleanPreferencesKey("is_selected_language")
    }

    val language: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[LANGUAGE]
    }

    val isSelectedLanguage: Flow<Boolean> = context.dataStore.data.map {preferences ->
        preferences[IS_SELECTED_LANGUAGE] ?: false
    }

    suspend fun saveLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE] = language
        }
    }

    suspend fun saveSelectedLanguage(isSelected: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_SELECTED_LANGUAGE] = isSelected
        }
    }
}