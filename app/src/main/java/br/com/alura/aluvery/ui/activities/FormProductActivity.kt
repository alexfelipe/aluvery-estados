package br.com.alura.aluvery.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import br.com.alura.aluvery.R
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Category
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.theme.AluveryTheme
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import java.math.BigDecimal

class FormProductActivity : ComponentActivity() {

    private val dao by lazy {
        ProductDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    FormVideoScreen(onSaveClick = {
                        lifecycleScope.launch {
                            dao.save(it)
                            finish()
                        }
                    })
                }
            }
        }
    }

}

@Composable
fun FormVideoScreen(
    state: FormProductState = rememberFormProductScreenState(),
    onSaveClick: (Product) -> Unit = {}
) {
    rememberScaffoldState()
    LazyColumn(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(
                text = "Criando o produto",
                Modifier.padding(bottom = 8.dp),
                fontSize = 28.sp
            )
        }
        item {
            if (state.url.isNotBlank()) {
                AsyncImage(
                    model = state.url,
                    contentDescription = null,
                    Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    error = painterResource(id = R.drawable.placeholder),
                    contentScale = ContentScale.Crop,
                )
            }
        }
        item {
            TextField(
                value = state.url,
                onValueChange = {
                    state.url = it
                },
                Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Url da imagem")
                }
            )
        }
        item {
            TextField(
                value = state.name,
                onValueChange = {
                    state.name = it
                },
                Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Nome")
                }
            )
        }
        item {
            TextField(
                value = state.price,
                onValueChange = {
                    state.price = it
                },
                Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Preço")
                }
            )
        }
        item {
            TextField(
                value = state.description,
                onValueChange = {
                    state.description = it
                },
                Modifier
                    .fillMaxWidth()
                    .heightIn(100.dp),
                label = {
                    Text(text = "Descrição")
                },
            )
        }
        item {
            Column {
                Text(
                    text = "Categorias",
                    fontSize = 24.sp
                )
                Category.values().forEach { category ->
                    var isChecked by remember {
                        mutableStateOf(false)
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (!isChecked) {
                                    state.categories.add(
                                        category
                                    )
                                } else {
                                    state.categories.remove(category)
                                }
                                isChecked = !isChecked
                            },
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = {
                                if (it) {
                                    state.categories.add(
                                        category
                                    )
                                } else {
                                    state.categories.remove(category)
                                }
                                isChecked = it
                            },
                        )
                        Text(text = category.label)
                    }
                }
            }
        }
        item {
            Button(
                onClick = {
                    onSaveClick(
                        state.product()
                    )
                },
                Modifier.fillMaxWidth()
            ) {
                Text("Salvar")
            }
        }
    }
}

@Composable
fun rememberFormProductScreenState(
    url: String = "",
    name: String = "",
    price: String = "",
    description: String = ""
) = remember {
    FormProductState(
        url = url,
        name = name,
        price = price,
        description = description
    )
}

class FormProductState(
    url: String = "",
    name: String = "",
    price: String = "",
    description: String = "",
) {

    var url by mutableStateOf(url)
    var name by mutableStateOf(name)
    var price by mutableStateOf(price)
    var description by mutableStateOf(description)
    val categories = mutableStateListOf<Category>()

    fun product(): Product {
        val verifiedPrice = try {
            BigDecimal(price)
        } catch (e: Exception) {
            BigDecimal("0.00")
        }
        return Product(
            name = name,
            price = verifiedPrice,
            image = url,
            description = description,
            categories = categories.toList()
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun FormProductScreenPreview() {
    FormVideoScreen()
}

@Preview(showBackground = true)
@Composable
fun FormVideoScreenWithUrlForImageScreen() {
    FormVideoScreen(rememberFormProductScreenState(url = "a"))
}