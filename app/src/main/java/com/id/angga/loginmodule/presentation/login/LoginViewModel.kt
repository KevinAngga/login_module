package com.id.angga.loginmodule.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.id.angga.loginmodule.data.preferences.PreferenceKeys
import com.id.angga.loginmodule.data.preferences.PreferenceKeys.USER_EMAIL
import com.id.angga.loginmodule.data.preferences.PreferenceKeys.USER_PASSWORD
import com.id.angga.loginmodule.domain.usecase.auth.AuthUseCase
import com.id.angga.loginmodule.domain.usecase.preferences.PreferenceUseCase
import com.id.angga.loginmodule.domain.usecase.validation.ValidateEmail
import com.id.angga.loginmodule.domain.usecase.validation.ValidatePassword
import com.id.angga.loginmodule.domain.util.Resource
import com.id.angga.loginmodule.presentation.navigation.Graph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val validateEmail : ValidateEmail,
    private val validatePassword : ValidatePassword,
    private val preferenceUseCase: PreferenceUseCase
) : ViewModel() {
    var loginViewState by mutableStateOf(LoginViewState())
        private set

    var loginFormState by mutableStateOf(LoginFormState())
        private set

//    init {
//        checkUserStatus()
//    }

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                loginFormState = loginFormState.copy(
                    email = event.email
                )
                validateEmail()
                buttonEnabled()
            }
            is LoginFormEvent.PasswordChanged -> {
                loginFormState = loginFormState.copy(
                    password = event.password
                )
                validatePassword()
                buttonEnabled()
            }
            is LoginFormEvent.Submit -> {
                login(
                    loginFormState.email,
                    loginFormState.password
                )
            }
        }
    }

    private fun checkUserStatus() {
        viewModelScope.launch {
            var loginUserEmail = ""
            var password = ""
            preferenceUseCase
                .readPreferences(
                    USER_EMAIL, "").collect { userEmail ->
                    loginUserEmail = userEmail

                    if (loginUserEmail.isNotEmpty()) {
                        loginViewState = loginViewState.copy(
                            isError = "",
                            isLogin = true
                        )
                    }
            }

//            preferenceUseCase
//                .readPreferences(
//                    USER_PASSWORD, "").collect { userPassword ->
//                    loginUserEmail = userEmail
//                }
        }
    }

    private fun validateEmail() : Boolean {
        val emailResult = validateEmail.execute(loginFormState.email)
        loginFormState = loginFormState.copy(
            emailError = emailResult.errorMessage,
            buttonEnable = emailResult.successful
        )
        return emailResult.successful
    }

    private fun validatePassword() : Boolean {
        val passwordResult = validatePassword.execute(loginFormState.password)
        loginFormState = loginFormState.copy(
            passwordError = passwordResult.errorMessage,
            buttonEnable = passwordResult.successful
        )
        return passwordResult.successful
    }

    private fun buttonEnabled() : Boolean {
        return validateEmail() && validatePassword()
    }

    private fun login(
        email : String,
        password : String
    ) {
        viewModelScope.launch {
            authUseCase.login.userLogin(email, password)
                .collect { resource ->
                    when(resource) {
                        is Resource.Error -> {
                            loginViewState = loginViewState.copy(
                                isError = resource.message ?: "Unknown Error",
                                isLoading = false,
                                isSuccess = null
                            )
                        }
                        is Resource.Loading -> {
                            loginViewState = loginViewState.copy(
                                isLoading = true,
                                isSuccess = null,
                                isError = ""
                            )
                        }
                        is Resource.Success -> {
                            loginViewState = loginViewState.copy(
                                isLoading = false,
                                isSuccess = resource.data,
                                isError = "",
                                isLogin = resource.data?.user?.email?.isNotEmpty() ?: false
                            )

                            resource.data?.user?.email?.let {
                                preferenceUseCase.savePreferences(USER_EMAIL,it)
                                preferenceUseCase.savePreferences(USER_PASSWORD, loginFormState.password)
                            }
                        }
                    }

                }
        }
    }

}