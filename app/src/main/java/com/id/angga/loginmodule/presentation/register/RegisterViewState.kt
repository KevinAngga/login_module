package com.id.angga.loginmodule.presentation.register

import com.google.firebase.auth.AuthResult

data class RegisterViewState(
    val isLoading : Boolean = false,
    val isSuccess : AuthResult? = null,
    val isError : String = ""
)
