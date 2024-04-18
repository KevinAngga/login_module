package com.id.angga.loginmodule.di

import com.id.angga.loginmodule.data.preferences.LocalUserPreferencesImpl
import com.id.angga.loginmodule.data.repository.AuthRepositoryImpl
import com.id.angga.loginmodule.domain.preferences.LocalUserPreferences
import com.id.angga.loginmodule.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBinds {
    @Binds
    abstract fun bindLocalUserPreferences(
        localUserPreferencesImpl: LocalUserPreferencesImpl
    ) : LocalUserPreferences

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ) : AuthRepository
}