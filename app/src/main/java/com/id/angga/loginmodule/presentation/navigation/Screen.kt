package com.id.angga.loginmodule.presentation.navigation

sealed class Screen(val route : String) {
    object HomePage : Screen("home")
    object ProfilePage : Screen("profile")
    object LogoutPage : Screen("logout")
    object LoginPage : Screen("login")
    object RegisterPage : Screen("register")
    object OnBoardingPage : Screen("onBoarding")
    object Auth : Screen("auth")
    object ReAuth : Screen("re_auth")
}