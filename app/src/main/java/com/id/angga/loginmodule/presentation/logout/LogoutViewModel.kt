package com.id.angga.loginmodule.presentation.logout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.id.angga.loginmodule.domain.usecase.preferences.PreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    val preferenceUseCase: PreferenceUseCase
) : ViewModel() {

    var logoutViewState by mutableStateOf(LogoutViewState())
        private set

    fun onEvent(event : LogoutViewEvent) {
        when(event) {
            LogoutViewEvent.Logout -> {
                logout()
            }
        }
    }


    private fun logout() {
        viewModelScope.launch {
            preferenceUseCase.clearPreferences.clearAll()
            logoutViewState = logoutViewState.copy(
                isLogout = true
            )
        }
    }

}