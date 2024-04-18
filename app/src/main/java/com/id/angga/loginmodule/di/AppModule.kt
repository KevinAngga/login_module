package com.id.angga.loginmodule.di

import com.google.firebase.auth.FirebaseAuth
import com.id.angga.loginmodule.domain.usecase.auth.AuthUseCase
import com.id.angga.loginmodule.domain.usecase.auth.Login
import com.id.angga.loginmodule.domain.usecase.auth.Register
import com.id.angga.loginmodule.domain.usecase.preferences.ClearPreferences
import com.id.angga.loginmodule.domain.usecase.preferences.PreferenceUseCase
import com.id.angga.loginmodule.domain.usecase.preferences.ReadPreferences
import com.id.angga.loginmodule.domain.usecase.preferences.SavePreferences
import com.id.angga.loginmodule.domain.usecase.validation.ValidateEmail
import com.id.angga.loginmodule.domain.usecase.validation.ValidatePassword
import com.id.angga.loginmodule.domain.usecase.validation.ValidateRepeatedPassword
import com.id.angga.loginmodule.domain.usecase.validation.ValidateTerms
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePreferencesUseCase(
        readPreferences: ReadPreferences,
        savePreferences: SavePreferences,
        clearPreferences: ClearPreferences
    ): PreferenceUseCase {
        return PreferenceUseCase(
            readPreferences = readPreferences,
            savePreferences = savePreferences,
            clearPreferences = clearPreferences
        )
    }

    @Provides
    @Singleton
    fun provideValidateEmail() : ValidateEmail {
        return ValidateEmail()
    }

    @Provides
    @Singleton
    fun provideValidatePassword() : ValidatePassword {
        return ValidatePassword()
    }

    @Provides
    @Singleton
    fun provideValidateRepeatedPassword() : ValidateRepeatedPassword {
        return ValidateRepeatedPassword()
    }

    @Provides
    @Singleton
    fun provideValidateTerms() : ValidateTerms {
        return ValidateTerms()
    }

    @Provides
    @Singleton
    fun provideAuthUseCase(
        login: Login,
        register: Register
    ) : AuthUseCase {
        return AuthUseCase(
            login = login,
            register = register
        )
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}