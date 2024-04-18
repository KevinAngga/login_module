package com.id.angga.loginmodule.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.id.angga.loginmodule.presentation.login.LoginScreen
import com.id.angga.loginmodule.presentation.login.LoginViewModel
import com.id.angga.loginmodule.presentation.register.RegisterScreen
import com.id.angga.loginmodule.presentation.register.RegisterViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = Screen.LoginPage.route,
        route = Graph.AUTHENTICATION
    ) {
        composable(
            route = Screen.LoginPage.route
        ) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                onEvent = viewModel::onEvent,
                loginViewState = viewModel.loginViewState,
                loginFormState = viewModel.loginFormState,
                navigateToRegister = {
                    navController.navigate(Screen.RegisterPage.route)
                },
                navigateToMain = {
                    navController.navigate(Graph.MAIN_SCREEN_PAGE) {
                        popUpTo(Screen.LoginPage.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable(
            route = Screen.RegisterPage.route
        ) {
            val viewModel = hiltViewModel<RegisterViewModel>()
            RegisterScreen(
                registerFormState = viewModel.registerFormState,
                registerViewState = viewModel.registerViewState,
                onEvent = viewModel::onEvent,
                navigateToLogIn = {
                    navController.popBackStack()
                }
            )
        }
    }
}