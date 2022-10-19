package br.com.alura.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.lifecycle.lifecycleScope
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.ui.screens.ProductFormScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme
import kotlinx.coroutines.launch

class ProductFormActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen(
                        onSaveClick = { product ->
                            lifecycleScope.launch {
                                dao.save(product)
                                finish()
                            }
                        })
                }
            }
        }
    }

}