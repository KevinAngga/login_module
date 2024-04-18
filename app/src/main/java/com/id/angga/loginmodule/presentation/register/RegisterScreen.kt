package com.id.angga.loginmodule.presentation.register

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.id.angga.loginmodule.R
import com.id.angga.loginmodule.presentation.components.CustomButton
import com.id.angga.loginmodule.presentation.components.CustomTextField
import com.id.angga.loginmodule.presentation.components.HyperlinkText
import com.id.angga.loginmodule.presentation.navigation.Screen
import com.id.angga.loginmodule.presentation.ui.theme.BgChecked
import com.id.angga.loginmodule.presentation.ui.theme.BgUnchecked
import com.id.angga.loginmodule.presentation.ui.theme.TextBlack
import com.id.angga.loginmodule.presentation.ui.theme.monserrat
import com.id.angga.loginmodule.presentation.ui.theme.monserratLight
import com.id.angga.loginmodule.presentation.ui.theme.monserratMedium


@Composable
fun RegisterScreen(
    registerFormState : RegisterFromState,
    registerViewState: RegisterViewState,
    onEvent: (RegistrationFormEvent) -> Unit,
    navigateToLogIn: () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LaunchedEffect(key1 = registerViewState) {
            if (registerViewState.isSuccess?.user?.email?.isNotEmpty() == true) {
                navigateToLogIn()
            }
        }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
                        text = "Get Started",
                        fontSize = 36.sp,
                        style = monserratMedium
                    )

                    Text(
                        text = "by creating a free account",
                        fontSize = 14.sp,
                        style = monserratLight
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {

                CustomTextField(
                    value = registerFormState.email,
                    labelText = stringResource(id = R.string.valid_email),
                    onValueChange = {
                        onEvent(RegistrationFormEvent.EmailChanged(it))
                    },
                    singleLine = true,
                    maxLine = 1,
                    keyboardType = KeyboardType.Text,
                    trailingIcon = Icons.Default.MailOutline,
                    isError = registerFormState.emailError != null,
                    errorMessage = registerFormState.emailError
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = registerFormState.password,
                    labelText = stringResource(id = R.string.password),
                    onValueChange = {
                        onEvent(RegistrationFormEvent.PasswordChanged(it))
                    },
                    singleLine = true,
                    maxLine = 1,
                    keyboardType = KeyboardType.Password,
                    trailingIcon = Icons.Default.Lock,
                    isError = registerFormState.passwordError != null,
                    errorMessage = registerFormState.passwordError
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = registerFormState.repeatPassword,
                    labelText = stringResource(id = R.string.re_enter_password),
                    onValueChange = {
                        onEvent(RegistrationFormEvent
                            .RepeatedPasswordChanged(it))
                    },
                    keyboardType = KeyboardType.Password,
                    trailingIcon = Icons.Default.Lock,
                    singleLine = true,
                    maxLine = 1,
                    isError = registerFormState.repeatPasswordError != null,
                    errorMessage = registerFormState.repeatPasswordError
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Card(
                        modifier = Modifier.background(Color.White),
                        elevation = CardDefaults.cardElevation(0.dp),
                        shape = RoundedCornerShape(3.dp),
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (registerFormState.acceptedTerms) BgChecked else BgUnchecked
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(
                                    if (registerFormState.acceptedTerms) BgChecked else Color.White
                                )
                                .clickable {
                                    onEvent(
                                        RegistrationFormEvent.AcceptTerms(
                                            !registerFormState.acceptedTerms
                                        )
                                    )
                                }
                        ) {
                            if (registerFormState.acceptedTerms)
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                        }
                    }

                    Spacer(modifier = Modifier.width(5.dp))

                    HyperlinkText(
                        fullText = "By checking the box you agree to our " + "Terms and Conditions.",
                        fontSize = 9.sp,
                        hyperLinks = mutableMapOf(
                            "Terms" to "/terms",
                            "Conditions" to "/condition"
                        ),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Center,
                            fontFamily = monserrat,
                            color = TextBlack
                        ),
                        linkTextColor = BgChecked,
                        linkTextFontWeight = FontWeight.Bold
                    ) {

                    }
                }
            }

            CustomButton(
                textButton = "Next",
                showIcon = true,
                enable = registerFormState.buttonEnable
            ) {
                onEvent(
                    RegistrationFormEvent.Submit
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            HyperlinkText(
                fullText = "Already a member? " + "Log in",
                hyperLinks = mutableMapOf(
                    "Log in" to Screen.LoginPage.route
                ),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontFamily = monserrat,
                    color = TextBlack
                ),
                linkTextColor = BgChecked,
                linkTextFontWeight = FontWeight.Bold
            ) {
                when (it) {
                    Screen.LoginPage.route -> {
                        navigateToLogIn()
                    }
                }
            }
        }

        if (registerViewState.isLoading) {
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
fun RegisterScreenPreview() {
    RegisterScreen(
        registerViewState = RegisterViewState(),
        registerFormState = RegisterFromState(),
        onEvent = {},
        navigateToLogIn = {}
    )
}