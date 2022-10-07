package br.com.alura.aluvery.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal

class ProductFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen()
                }
            }
        }
    }

}

@Composable
fun ProductFormScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Criando o produto",
            Modifier.fillMaxWidth(),
            fontSize = 28.sp,
        )
        var url by remember {
            mutableStateOf("")
        }
        TextField(
            value = url,
            onValueChange = {
                url = it
            },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Url da imagem")
            },
        )
        var name by remember {
            mutableStateOf("")
        }
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome")
            },
        )
        var price by remember {
            mutableStateOf("")
        }
        TextField(
            value = price,
            onValueChange = {
                price = it
            },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Preço")
            },
        )
        var description by remember {
            mutableStateOf("")
        }
        TextField(
            value = description,
            onValueChange = {
                description = it
            },
            Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = {
                Text(text = "Descrição")
            },
        )
        Button(
            onClick = {
                val convertedPrice = try {
                    BigDecimal(price)
                } catch (e: NumberFormatException) {
                    BigDecimal.ZERO
                }
                val product = Product(
                    name = name,
                    image = url,
                    price = convertedPrice,
                    description = description
                )
                Log.i("ProductFormActivity", "ProductFormScreen: $product")
            },
            Modifier.fillMaxWidth(),
        ) {
            Text(text = "Salvar")
        }
    }
}

@Preview
@Composable
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
        }
    }
}