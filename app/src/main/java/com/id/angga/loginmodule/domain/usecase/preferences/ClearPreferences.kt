package com.id.angga.loginmodule.domain.usecase.preferences

import com.id.angga.loginmodule.domain.preferences.LocalUserPreferences
import javax.inject.Inject

class ClearPreferences @Inject constructor(
    private val localUserPreferences: LocalUserPreferences
){
    suspend fun clearAll() = localUserPreferences.clearAllPreference()
}