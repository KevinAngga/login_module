package com.id.angga.loginmodule.domain.preferences

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface LocalUserPreferences {
    suspend fun<T> getPreference(key: Preferences.Key<T>, defaultValue : T): Flow<T>
    suspend fun <T> getPreferences(keys: List<Preferences.Key<T>>, defaultValues: Map<Preferences.Key<T>, T>)
    : Flow<Map<Preferences.Key<T>, T>>
    suspend fun<T> putPreference(key: Preferences.Key<T>, value: T)
    suspend fun<T> removePreference(key: Preferences.Key<T>)
    suspend fun clearAllPreference()
}