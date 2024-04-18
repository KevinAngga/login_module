package com.id.angga.loginmodule.presentation.re_auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.id.angga.loginmodule.R
import com.id.angga.loginmodule.domain.biometric.BiometricPromptManager
import com.id.angga.loginmodule.domain.biometric.BiometricResult
import com.id.angga.loginmodule.presentation.ui.theme.monserrat

@Composable
fun ReAuthScreen(
    biometricPromptManager: BiometricPromptManager,
    navigateToMain: () -> Unit,
) {
    val biometricResult by biometricPromptManager
        .promptResult
        .collectAsState(
            initial = BiometricResult.Init
        )


    LaunchedEffect(biometricPromptManager) {
        if (biometricResult == BiometricResult.Init) {
            biometricPromptManager.showBiometricPrompt(
                title = "Biometric Verification",
                description = "Use biometric to open"
            )
        }
    }



    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        biometricResult.let { result ->
            when (result) {
                is BiometricResult.AuthenticationError -> {
                    result.error
                }

                BiometricResult.AuthenticationFailed -> {
                    println("-- Authentication failed")
                }

                BiometricResult.AuthenticationNotSet -> {
                    println("-- Authentication not set")
                }

                BiometricResult.AuthenticationSuccess -> {
                    println("-- Authentication success")
                    navigateToMain()
                }

                BiometricResult.FeatureUnavailable -> {
                    println("-- Feature unavailable")
                }

                BiometricResult.HardwareUnavailable -> {
                    println("-- Hardware unavailable")
                }

                BiometricResult.Init -> {
                    println("-- Initialize")
                }
            }
        }

        Text(
            text = stringResource(id = R.string.re_auth_page),
            fontFamily = monserrat,
            fontWeight = FontWeight.Bold
        )
    }
}