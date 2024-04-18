package com.id.angga.loginmodule.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.id.angga.loginmodule.presentation.home.HomeScreen
import com.id.angga.loginmodule.presentation.logout.LogoutScreen
import com.id.angga.loginmodule.presentation.logout.LogoutViewModel
import com.id.angga.loginmodule.presentation.navigation.bottom_navigation.BottomNavigationScreen
import com.id.angga.loginmodule.presentation.profile.ProfileScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN_SCREEN_PAGE,
        startDestination = BottomNavigationScreen.Home.route
    ) {
        composable(route = BottomNavigationScreen.Home.route) {
            HomeScreen()
        }

        composable(route = BottomNavigationScreen.Profile.route) {
            ProfileScreen()
        }

        composable(route = BottomNavigationScreen.Logout.route) {
            val viewModel = hiltViewModel<LogoutViewModel>()
            LogoutScreen(
                onEvent = viewModel::onEvent,
                logoutViewState = viewModel.logoutViewState,
                navigateToLogin = {
                    navController.navigate(Screen.LoginPage.route)
                }
            )
        }

        authNavGraph(navController)
    }
}