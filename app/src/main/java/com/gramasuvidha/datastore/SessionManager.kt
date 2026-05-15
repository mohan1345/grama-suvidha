package com.gramasuvidha.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "grama_suvidha_prefs")

@Singleton
class SessionManager @Inject constructor(@ApplicationContext context: Context) {
    private val dataStore = context.dataStore

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val ROLE = stringPreferencesKey("role")
        val USER_ID = intPreferencesKey("user_id")
        val LANGUAGE = stringPreferencesKey("language") // "en" or "kn"
    }

    val isLoggedIn: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IS_LOGGED_IN] ?: false
    }

    val role: Flow<String?> = dataStore.data.map { preferences ->
        preferences[ROLE]
    }

    val userId: Flow<Int?> = dataStore.data.map { preferences ->
        preferences[USER_ID]
    }

    val language: Flow<String> = dataStore.data.map { preferences ->
        preferences[LANGUAGE] ?: "en"
    }

    suspend fun saveSession(isLoggedIn: Boolean, role: String, userId: Int) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
            preferences[ROLE] = role
            preferences[USER_ID] = userId
        }
    }

    suspend fun saveLanguage(lang: String) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE] = lang
        }
    }

    suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.remove(IS_LOGGED_IN)
            preferences.remove(ROLE)
            preferences.remove(USER_ID)
        }
    }
}
