package com.id.angga.loginmodule.presentation.login

import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.biometric.BiometricManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import com.id.angga.loginmodule.MainActivity
import com.id.angga.loginmodule.R
import com.id.angga.loginmodule.domain.biometric.BiometricPromptManager
import com.id.angga.loginmodule.domain.biometric.BiometricResult
import com.id.angga.loginmodule.presentation.components.CustomButton
import com.id.angga.loginmodule.presentation.components.CustomTextField
import com.id.angga.loginmodule.presentation.components.HyperlinkText
import com.id.angga.loginmodule.presentation.navigation.Screen
import com.id.angga.loginmodule.presentation.ui.theme.BgChecked
import com.id.angga.loginmodule.presentation.ui.theme.BgUnchecked
import com.id.angga.loginmodule.presentation.ui.theme.TextBlack
import com.id.angga.loginmodule.presentation.ui.theme.arial
import com.id.angga.loginmodule.presentation.ui.theme.monserrat
import com.id.angga.loginmodule.presentation.ui.theme.monserratLight
import com.id.angga.loginmodule.presentation.ui.theme.monserratMedium


@Composable
fun LoginScreen(
    onEvent : (LoginFormEvent) -> Unit,
    loginFormState: LoginFormState,
    loginViewState: LoginViewState,
    navigateToRegister: () -> Unit,
    navigateToMain: () -> Unit,
) {
    val isChecked = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LaunchedEffect(key1 = loginViewState) {
            if (loginViewState.isLogin) {
                Log.e("this is login", "Login")
                navigateToMain()
            }
        }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 14.dp),
                    contentAlignment = Alignment.Center,
                ) {

                    Image(
                        modifier = Modifier.padding(start = 52.dp),
                        painter = painterResource(R.drawable.login_img),
                        contentDescription = null
                    )

                    Column {
                        Spacer(modifier = Modifier.height(64.dp))
                        Text(
                            text = "Welcome back",
                            fontSize = 24.sp,
                            style = monserratMedium
                        )

                        Text(
                            text = "sign in to access your account",
                            fontSize = 14.sp,
                            style = monserratLight
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                CustomTextField(
                    value = loginFormState.email,
                    labelText = stringResource(id = R.string.enter_email),
                    onValueChange = {
                        onEvent(LoginFormEvent.EmailChanged(it))
                    },
                    singleLine = true,
                    maxLine = 1,
                    keyboardType = KeyboardType.Text,
                    trailingIcon = Icons.Default.MailOutline,
                    isError = loginFormState.emailError != null,
                    errorMessage = loginFormState.emailError
                )

                Spacer(modifier = Modifier.height(24.dp))

                CustomTextField(
                    value = loginFormState.password,
                    labelText = stringResource(id = R.string.password),
                    onValueChange = {
                        onEvent(LoginFormEvent.PasswordChanged(it))
                    },
                    singleLine = true,
                    maxLine = 1,
                    keyboardType = KeyboardType.Password,
                    trailingIcon = Icons.Default.Lock,
                    isError = loginFormState.passwordError != null,
                    errorMessage = loginFormState.passwordError
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Card(
                            modifier = Modifier.background(Color.White),
                            elevation = CardDefaults.cardElevation(0.dp),
                            shape = RoundedCornerShape(3.dp),
                            border = BorderStroke(width = 1.dp,
                                color = if (isChecked.value) BgChecked else  BgUnchecked)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(
                                        if (isChecked.value) BgChecked else Color.White
                                    )
                                    .clickable {
                                        isChecked.value = !isChecked.value
                                        //event to remember
                                    }
                            ) {
                                if(isChecked.value)
                                    Icon(Icons.Default.Check, contentDescription = "", tint = Color.White)
                            }
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "Remember me",
                            fontSize = 9.sp,
                            fontFamily = arial
                        )

                    }

                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Forget password?",
                            fontSize = 9.sp,
                            fontFamily = arial,
                            color = BgChecked
                        )
                    }
                }
            }


            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomButton(
                    textButton = "Next",
                    enable = loginFormState.buttonEnable,
                    showIcon = true,
                ) {
                    onEvent(
                        LoginFormEvent.Submit
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                HyperlinkText(
                    fullText = "New Member? " + "Register now",
                    hyperLinks = mutableMapOf(
                        "Register now" to Screen.RegisterPage.route
                    ),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = monserrat,
                        color = TextBlack
                    ),
                    linkTextColor = BgChecked,
                    linkTextFontWeight = FontWeight.Bold,
                    handleLink = {
                        when(it) {
                            Screen.RegisterPage.route -> {
                                navigateToRegister()
                            }
                        }
                    }
                )
            }
        }

        if (loginViewState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .background(Color.Red)
                    .align(Alignment.Center)
            )
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onEvent = {},
        loginFormState = LoginFormState(),
        loginViewState = LoginViewState(),
        navigateToRegister = {},
        navigateToMain = {},
//        biometricPromptManager = remember {
//            BiometricPromptManager(activity = MainActivity())
//        }
    )
}