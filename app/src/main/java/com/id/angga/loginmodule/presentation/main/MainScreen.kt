package com.id.angga.loginmodule.presentation.main

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.id.angga.loginmodule.presentation.navigation.MainNavGraph
import com.id.angga.loginmodule.presentation.navigation.bottom_navigation.BottomNavigationScreen
import com.id.angga.loginmodule.presentation.ui.theme.monserratMedium

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    val screens = listOf(
        BottomNavigationScreen.Home,
        BottomNavigationScreen.Profile,
        BottomNavigationScreen.Logout
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBar {
                    screens.forEachIndexed { index, bottomNavigationScreen ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any() {
                                it.route == bottomNavigationScreen.route
                            } == true,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(bottomNavigationScreen.route) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            },
                            label = {
                                Text(
                                    text = bottomNavigationScreen.title,
                                    style = monserratMedium,
                                    fontSize = 12.sp
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = bottomNavigationScreen.icon,
                                    contentDescription = bottomNavigationScreen.title
                                )
                            }
                        )
                    }
                }
            }
        }
    ) {
        val padding = Modifier.padding(it)
        Log.e("this is main", "main screen")
        MainNavGraph(navController = navController)
    }
}