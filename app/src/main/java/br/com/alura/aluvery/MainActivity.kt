package br.com.alura.aluvery

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.alura.aluvery.ui.screens.HomeScreen
import br.com.alura.aluvery.ui.screens.ProfileScreen
import br.com.alura.aluvery.ui.screens.SignInScreen
import br.com.alura.aluvery.ui.screens.SignUpScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    AluveryTheme {
        Surface {
            val navController = rememberNavController()
            AppNavHost(navController)
        }
    }
}

sealed class Screen(val route: String, val icon: ImageVector) {
    object Home : Screen("home", Icons.Default.Home)
    object Profile : Screen("profile", Icons.Default.Person)
}

@Composable
private fun AppNavHost(navController: NavHostController) {
    val items = listOf(Screen.Home, Screen.Profile)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val route = currentDestination?.route ?: Screen.Home.route
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                route == Screen.Home.route ||
                        route == Screen.Profile.route
            ) {
                BottomNavigation {
                    items.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(screen.route) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            Modifier.padding(contentPadding)
        ) {
            composable("home") {
                HomeScreen()
            }
            composable("profile") {
                ProfileScreen {
                    navController.navigate("signIn")
                }
            }
            composable("signIn") {
                SignInScreen(
                    onSignIn = { user ->
                        Log.i("MainActivity", "App: $user")
                        navController.navigate("home") {
                            popUpTo("signIn") {
                                inclusive = true
                            }
                        }
                    },
                    onSignUp = {
                        navController.navigate("signUp")
                    }
                )
            }
            composable("signUp") {
                SignUpScreen(onSignUp = {
                    navController.navigate("signIn") {
                        popUpTo("signUp") {
                            inclusive = true
                        }
                    }
                })
            }
        }
    }
}