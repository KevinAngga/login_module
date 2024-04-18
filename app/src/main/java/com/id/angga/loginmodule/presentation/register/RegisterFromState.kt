package com.id.angga.loginmodule.presentation.register

import com.id.angga.loginmodule.presentation.UiText

data class RegisterFromState(
    val email : String = "",
    val emailError : UiText? = null,
    val password : String = "",
    val passwordError : UiText? = null,
    val repeatPassword : String = "",
    val repeatPasswordError : UiText? = null,
    val acceptedTerms: Boolean = false,
    val termsError : UiText? = null,
    val buttonEnable : Boolean = false
)
