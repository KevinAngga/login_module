package com.id.angga.loginmodule.presentation.navigation.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.id.angga.loginmodule.presentation.navigation.Screen

sealed class BottomNavigationScreen (
    val route : String,
    val title : String,
    val icon : ImageVector
) {
    object Home : BottomNavigationScreen(
        route = Screen.HomePage.route,
        title = Screen.HomePage.route,
        icon = Icons.Default.Home
    )

    object Profile : BottomNavigationScreen(
        route = Screen.ProfilePage.route,
        title = Screen.ProfilePage.route,
        icon = Icons.Default.AccountCircle
    )

    object Logout : BottomNavigationScreen(
        route = Screen.LogoutPage.route,
        title = Screen.LogoutPage.route,
        icon = Icons.Default.Settings
    )
}