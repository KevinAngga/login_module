package com.id.angga.loginmodule.presentation.register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.id.angga.loginmodule.domain.usecase.auth.AuthUseCase
import com.id.angga.loginmodule.domain.usecase.validation.ValidateEmail
import com.id.angga.loginmodule.domain.usecase.validation.ValidatePassword
import com.id.angga.loginmodule.domain.usecase.validation.ValidateRepeatedPassword
import com.id.angga.loginmodule.domain.usecase.validation.ValidateTerms
import com.id.angga.loginmodule.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateRepeatedPassword: ValidateRepeatedPassword,
    private val validateTerms: ValidateTerms
) : ViewModel() {

    var registerViewState by mutableStateOf(RegisterViewState())
        private set

    var registerFormState by mutableStateOf(RegisterFromState())
        private set

    fun onEvent(event: RegistrationFormEvent) {
        when(event) {
            is RegistrationFormEvent.AcceptTerms -> {
                registerFormState = registerFormState.copy(
                    acceptedTerms = event.isAccepted
                )
                validateTerms()
                buttonEnabled()
            }

            is RegistrationFormEvent.EmailChanged -> {
                registerFormState = registerFormState.copy(
                    email = event.email
                )
                validateEmail()
                buttonEnabled()
            }

            is RegistrationFormEvent.PasswordChanged -> {
                registerFormState = registerFormState.copy(
                    password = event.password
                )
                validatePassword()
                buttonEnabled()
            }

            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                registerFormState = registerFormState.copy(
                    repeatPassword = event.repeatedPassword
                )
                validateRepeatedPassword()
                buttonEnabled()
            }

            is RegistrationFormEvent.Submit -> {
                register(
                    registerFormState.email,
                    registerFormState.password
                )
            }
        }
    }

    private fun validateEmail() : Boolean {
        val emailResult = validateEmail.execute(registerFormState.email)
        registerFormState = registerFormState.copy(
            emailError = emailResult.errorMessage,
            buttonEnable = emailResult.successful
        )
        return emailResult.successful
    }

    private fun validatePassword() : Boolean {
        val passwordResult = validatePassword.execute(registerFormState.password)
        registerFormState = registerFormState.copy(
            passwordError = passwordResult.errorMessage,
            buttonEnable = passwordResult.successful
        )
        return passwordResult.successful
    }

    private fun validateRepeatedPassword() : Boolean {
        val repeatedPassword = validateRepeatedPassword.execute(registerFormState.password,
            registerFormState.repeatPassword)
        registerFormState = registerFormState.copy(
            repeatPasswordError = repeatedPassword.errorMessage,
            buttonEnable = repeatedPassword.successful
        )
        return repeatedPassword.successful
    }

    private fun validateTerms() : Boolean {
        val terms = validateTerms.execute(registerFormState.acceptedTerms)
        registerFormState = registerFormState.copy(
            termsError = terms.errorMessage,
            buttonEnable = terms.successful
        )
        return terms.successful
    }

    private fun buttonEnabled(): Boolean {
        return validateEmail() && validatePassword() && validateRepeatedPassword() && validateTerms()
    }

    private fun register(
        email : String,
        password : String
    ) {
        viewModelScope.launch {
            authUseCase.register.userRegister(email, password)
                .collect { resource ->
                    when(resource) {
                        is Resource.Error -> {
                            registerViewState = registerViewState.copy(
                                isError = resource.message ?: "Unknown Error",
                                isLoading = false,
                                isSuccess = null
                            )
                        }
                        is Resource.Loading -> {
                            registerViewState = registerViewState.copy(
                                isLoading = true,
                                isSuccess = null,
                                isError = ""
                            )
                        }
                        is Resource.Success -> {
                            registerViewState = registerViewState.copy(
                                isLoading = false,
                                isSuccess = resource.data,
                                isError = ""
                            )
                        }
                    }
                }
        }
    }
}