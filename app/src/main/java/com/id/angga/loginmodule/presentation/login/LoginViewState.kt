package com.id.angga.loginmodule.presentation.login

import com.google.firebase.auth.AuthResult

data class LoginViewState (
    val isLoading : Boolean = false,
    val isSuccess : AuthResult? = null,
    val isError : String = "",
    val isLogin : Boolean = false
)