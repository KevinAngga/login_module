package com.id.angga.loginmodule.domain.usecase.preferences

class PreferenceUseCase (
    val readPreferences: ReadPreferences,
    val savePreferences: SavePreferences,
    val clearPreferences: ClearPreferences
)