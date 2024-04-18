package com.id.angga.loginmodule.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.id.angga.loginmodule.data.preferences.PreferenceKeys
import com.id.angga.loginmodule.domain.usecase.preferences.PreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val useCase: PreferenceUseCase
) : ViewModel() {
     fun saveOnBoardingStatus() {
        viewModelScope.launch {
            useCase.savePreferences(PreferenceKeys.ONBOARDING_STATUS, true)
        }
    }
}