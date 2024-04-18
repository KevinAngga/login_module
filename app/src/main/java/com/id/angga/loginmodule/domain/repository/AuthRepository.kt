package com.id.angga.loginmodule.domain.repository

import com.google.firebase.auth.AuthResult
import com.id.angga.loginmodule.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email : String, password : String) : Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String) : Flow<Resource<AuthResult>>
}