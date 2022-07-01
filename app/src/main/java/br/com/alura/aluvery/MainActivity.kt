package br.com.alura.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import br.com.alura.aluvery.sampledata.sampleMappedProducts
import br.com.alura.aluvery.ui.screens.HomeScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme
import java.util.*

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
            var desiredProduct by remember {
                mutableStateOf("")
            }
            HomeScreen(
                sampleMappedProducts,
                desiredProduct = desiredProduct,
                onDesiredTextChange = {
                    desiredProduct = it
                }
            )
        }
    }
}