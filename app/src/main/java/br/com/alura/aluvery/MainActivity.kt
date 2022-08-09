package br.com.alura.aluvery

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.alura.aluvery.sampledata.sampleMappedProducts
import br.com.alura.aluvery.ui.screens.AuthenticationState
import br.com.alura.aluvery.ui.screens.HomeScreen
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
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") {
                    HomeScreen(
                        sampleMappedProducts
                    )
                }
                composable("searchProducts") {
                    TODO("need to implement the searchProducts")
                }
                composable("profile") {
                    TODO("need to implement the profile")
                }
                composable("authentication") {
                    TODO("need to implement the authentication")
                }
            }
        }
    }
}