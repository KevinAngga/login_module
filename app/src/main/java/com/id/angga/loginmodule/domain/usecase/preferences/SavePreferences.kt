package com.id.angga.loginmodule.domain.usecase.preferences

import androidx.datastore.preferences.core.Preferences
import com.id.angga.loginmodule.domain.preferences.LocalUserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavePreferences @Inject constructor(
    private val localUserPreferences: LocalUserPreferences
) {
    suspend operator fun <T> invoke(
        key: Preferences.Key<T>,
        value: T
    ) =  localUserPreferences.putPreference(key, value)
}