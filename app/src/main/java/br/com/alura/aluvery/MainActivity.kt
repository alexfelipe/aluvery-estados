package br.com.alura.aluvery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.activities.FormProductActivity
import br.com.alura.aluvery.ui.screens.HomeScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {

    private val dao by lazy {
        ProductDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                onClickFab = {
                    startActivity(
                        Intent(this, FormProductActivity::class.java)
                    )
                }
            ) {
                val products by dao.products.collectAsState(initial = emptyList())
                val sections = mapOf(
                    "Todos produtos" to products
                )
                Log.i("MainActivity", "onCreate: $sections")
                HomeScreen(
                    sections = sections,
                    Modifier.padding(it),
                )
            }
        }
    }
}

@Composable
fun App(
    onClickFab: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {}
) {
    AluveryTheme {
        Surface {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            onClickFab()
                        },
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }
            ) {
                content(it)
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App {

    }
}