package com.id.angga.loginmodule

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.id.angga.loginmodule.data.preferences.PreferenceKeys.ONBOARDING_STATUS
import com.id.angga.loginmodule.data.preferences.PreferenceKeys.USER_EMAIL
import com.id.angga.loginmodule.domain.usecase.preferences.PreferenceUseCase
import com.id.angga.loginmodule.presentation.navigation.Graph
import com.id.angga.loginmodule.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: PreferenceUseCase
) : ViewModel() {
    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Graph.ON_BOARDING)
    val startDestination : State<String> = _startDestination

    init {
        saveOnboardingStatus()
    }

//    private fun saveOnboardingStatus() {
//        viewModelScope.launch {
//            val userEmail = useCase.readPreferences(USER_EMAIL, "").first()
//            val onBoardingStatus = useCase.readPreferences(ONBOARDING_STATUS, false).first()
//            if (onBoardingStatus) {
//                if (userEmail.isNotEmpty()) {
//                    _startDestination.value = Graph.MAIN_SCREEN_PAGE
//                } else {
//                    _startDestination.value = Graph.AUTHENTICATION
//                }
//            } else {
//                _startDestination.value = Graph.ON_BOARDING
//            }
//            Log.e("this start", "start dest "+_startDestination.value)
//            delay(2000)
//            _splashCondition.value = false
//        }
//    }

   private fun saveOnboardingStatus() {
       viewModelScope.launch {
           val readUserEmail = async { useCase.readPreferences(USER_EMAIL, "") }
           val readOnBoardingStatus = async { useCase.readPreferences( ONBOARDING_STATUS, false) }

           val userEmail = readUserEmail.await()
           val onBoardingStatus = readOnBoardingStatus.await()

           onBoardingStatus.collect { status ->
               if (status) {
                   Log.e("this", "collect status")
                   userEmail.collect {email ->
                       Log.e("this", "collect email")
                       if (email.isNotEmpty()) {
                           //change to pin graph
                           _startDestination.value = Screen.ReAuth.route
                       } else {
                           _startDestination.value = Graph.AUTHENTICATION
                       }
                       delay(200)
                       _splashCondition.value = false
                   }
               } else {
                   _startDestination.value = Graph.ON_BOARDING
               }
               delay(200)
               _splashCondition.value = false
           }
       }
   }
}