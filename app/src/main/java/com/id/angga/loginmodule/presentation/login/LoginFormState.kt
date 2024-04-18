package com.id.angga.loginmodule.presentation.login

import com.id.angga.loginmodule.presentation.UiText

data class LoginFormState(
    val email : String = "",
    val emailError : UiText? = null,
    val password : String = "",
    val passwordError : UiText? = null,
    val buttonEnable : Boolean = false
)