package com.id.angga.loginmodule

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.id.angga.loginmodule.presentation.navigation.Graph
import com.id.angga.loginmodule.presentation.navigation.RootNavigationGraph
import com.id.angga.loginmodule.presentation.navigation.bottom_navigation.BottomNavigationScreen
import com.id.angga.loginmodule.presentation.ui.theme.LoginModuleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<MainViewModel>()
        installSplashScreen()
            .apply {
                setKeepOnScreenCondition(
                    condition = {
                        viewModel.splashCondition.value
                    }
                )
            }

        setContent {
            LoginModuleTheme {
                // A surface container using the 'background' color from the theme
                ChangeSystemBarsTheme(lightTheme = !isSystemInDarkTheme())
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Log.e("this main activity", "main activity jalan lagi")
                    Log.e("this main activity", "start dest value on main "+viewModel.startDestination.value)
                    RootNavigationGraph(
                        startDestination = viewModel.startDestination.value,
                        activity = this
                    )

//                    Scaffold(
//                        bottomBar = {
//                            NavigationBar {
//                                screens.forEachIndexed { index, bottomNavigationScreen ->
//                                    NavigationBarItem(
//                                        selected = false,
//                                        onClick = {
//                                        },
//                                        icon = {
//                                            Icon(
//                                                imageVector = bottomNavigationScreen.icon,
//                                                contentDescription = bottomNavigationScreen.title)
//                                        }
//                                    )
//                                }
//                            }
//                        }
//                    ) {
//                        val navController = rememberNavController()
//                        Box(
//                            modifier = Modifier.padding(it)
//                        ) {
//                            NavGraph(
//                                startDestination = viewModel.startDestination.value,
//                                activity = activity,
//                                navHostController = navController
//                            )
//                        }
//                    }
                }
            }
        }
    }
}

@Composable
private fun AppCompatActivity.ChangeSystemBarsTheme(lightTheme: Boolean) {
    val barColor = Color.Transparent.toArgb()
    LaunchedEffect(lightTheme) {
        if (lightTheme) {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.light(
                    barColor, barColor,
                ),
                navigationBarStyle = SystemBarStyle.light(
                    barColor, barColor,
                ),
            )
        } else {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.dark(
                    barColor,
                ),
                navigationBarStyle = SystemBarStyle.dark(
                    barColor,
                ),
            )
        }
    }
}