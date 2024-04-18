package com.id.angga.loginmodule.presentation.logout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.id.angga.loginmodule.R
import com.id.angga.loginmodule.presentation.components.CustomButton
import com.id.angga.loginmodule.presentation.ui.theme.monserratMedium

@Composable
fun LogoutScreen(
    onEvent: (LogoutViewEvent) -> Unit,
    navigateToLogin : () -> Unit,
    logoutViewState: LogoutViewState
) {

    LaunchedEffect(key1 = logoutViewState) {
        if (logoutViewState.isLogout) {
            navigateToLogin()
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.welcome_logout),
            fontSize = 24.sp,
            style = monserratMedium
        )

        Spacer(modifier = Modifier.height(48.dp))

        CustomButton(
            textButton = stringResource(id = R.string.log_me_out),
            showIcon = false,
            enable = true
        ) {
            onEvent(LogoutViewEvent.Logout)
        }
    }
}


@Composable
@Preview
fun LogoutScreenPreview() {
    LogoutScreen(
        onEvent = {},
        logoutViewState = LogoutViewState(),
        navigateToLogin = {}
    )
}