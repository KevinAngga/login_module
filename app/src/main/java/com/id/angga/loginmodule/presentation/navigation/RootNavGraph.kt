package com.id.angga.loginmodule.presentation.navigation

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.id.angga.loginmodule.domain.biometric.BiometricPromptManager
import com.id.angga.loginmodule.presentation.main.MainScreen
import com.id.angga.loginmodule.presentation.navigation.bottom_navigation.BottomNavigationScreen
import com.id.angga.loginmodule.presentation.onboarding.OnBoardingScreen
import com.id.angga.loginmodule.presentation.re_auth.ReAuthScreen


@Composable
fun RootNavigationGraph(
    startDestination: String,
    activity: AppCompatActivity,
    navHostController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        Log.e("this is root nav", "root nav")
        composable(route = Graph.ON_BOARDING) {
            OnBoardingScreen(
                navigateToLogin = {
                    navHostController.navigate(Graph.AUTHENTICATION)
                }
            )
        }
        authNavGraph(navHostController)
        composable(route = Graph.MAIN_SCREEN_PAGE) {
           MainScreen()
        }

        composable(route = Graph.RE_AUTH) {
            ReAuthScreen(
                biometricPromptManager = BiometricPromptManager(activity),
                navigateToMain = {
                    navHostController.navigate(Graph.MAIN_SCREEN_PAGE) {
                        popUpTo(Screen.ReAuth.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}