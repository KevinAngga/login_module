package com.id.angga.loginmodule.domain.usecase.preferences

import androidx.datastore.preferences.core.Preferences
import com.id.angga.loginmodule.domain.preferences.LocalUserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadPreferences @Inject constructor(
    private val localUserPreferences: LocalUserPreferences
) {
    suspend operator fun <T> invoke(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> = localUserPreferences.getPreference(key, defaultValue)

    suspend fun <T> getPreferences(keys: List<Preferences.Key<T>>,
    defaultValues: Map<Preferences.Key<T>, T>): Flow<Map<Preferences.Key<T>, T>> = localUserPreferences.getPreferences(
        keys, defaultValues
    )
}