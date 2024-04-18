package com.id.angga.loginmodule.data.preferences

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.id.angga.loginmodule.domain.preferences.LocalUserPreferences
import com.id.angga.loginmodule.utills.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class LocalUserPreferencesImpl @Inject constructor(
    private val application: Application
) : LocalUserPreferences {
    override suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return application.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val result = preferences[key] ?: defaultValue
            result
        }
    }

    override suspend fun <T> getPreferences(keys: List<Preferences.Key<T>>,
                                            defaultValues: Map<Preferences.Key<T>, T>): Flow<Map<Preferences.Key<T>, T>> {
        return application.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            keys.associateWith { key ->
                preferences[key] ?: defaultValues[key] ?: error("No default value found for key: $key")
            }
        }
    }

    override suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        application.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        application.dataStore.edit {
            it.remove(key)
        }
    }

    override suspend fun clearAllPreference() {
        application.dataStore.edit {
            it.clear()
        }
    }
}

private val readOnlyProperty = preferencesDataStore(name = Constants.USER_SETTINGS)
val Context.dataStore: DataStore<Preferences> by readOnlyProperty

object PreferenceKeys {
    val ONBOARDING_STATUS = booleanPreferencesKey(Constants.ONBOARDING_STATUS)
    val USER_EMAIL = stringPreferencesKey(Constants.USER_EMAIL)
    val USER_PASSWORD = stringPreferencesKey(Constants.USER_PASSWORD)
}