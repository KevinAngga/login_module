package com.id.angga.loginmodule.domain.usecase.auth

import com.google.firebase.auth.AuthResult
import com.id.angga.loginmodule.domain.repository.AuthRepository
import com.id.angga.loginmodule.domain.util.Resource
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class Login @Inject constructor(
    private val authRepository: AuthRepository
) {
    fun userLogin(email : String, password : String) : Flow<Resource<AuthResult>> {
       return  authRepository.loginUser(email, password)
    }
}