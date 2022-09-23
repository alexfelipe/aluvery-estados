package br.com.alura.aluvery.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
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
            FormVideoScreen(onSaveClick = {
                lifecycleScope.launch {
                    dao.save(it)
                    finish()
                }
            })
        }
    }

}

@Composable
fun FormVideoScreen(
    imageUrl: String = "",
    onSaveClick: (Product) -> Unit = {}
) {
    var urlState by remember {
        mutableStateOf(imageUrl)
    }
    var nameState by remember {
        mutableStateOf("")
    }
    var priceState by remember {
        mutableStateOf("")
    }
    var descriptionState by remember {
        mutableStateOf("")
    }
    val categoriesState = remember {
        mutableStateListOf<Category>()
    }
    Log.i("FormProductActivity", "FormVideoScreen: $categoriesState")
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
        //TODO implementar debounce
        if (urlState.isNotBlank()) {
            item {
                AsyncImage(
                    model = urlState,
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
                value = urlState,
                onValueChange = {
                    urlState = it
                },
                Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Url da imagem")
                }
            )
        }
        item {
            TextField(
                value = nameState,
                onValueChange = {
                    nameState = it
                },
                Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Nome")
                }
            )
        }
        item {
            TextField(
                value = priceState,
                onValueChange = {
                    priceState = it
                },
                Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Preço")
                }
            )
        }
        item {
            TextField(
                value = descriptionState,
                onValueChange = {
                    descriptionState = it
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
                                if(!isChecked) {
                                    categoriesState.add(
                                        category
                                    )
                                } else {
                                    categoriesState.remove(category)
                                }
                                isChecked = !isChecked
                            },
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = {
                                if(it) {
                                    categoriesState.add(
                                        category
                                    )
                                } else {
                                    categoriesState.remove(category)
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
                    val price = try {
                        BigDecimal(priceState)
                    } catch (e: Exception) {
                        BigDecimal("0.00")
                    }
                    val categories = categoriesState.toList()
                    Log.i("FormVideoActivity", "FormVideoScreen: $categories")
                    onSaveClick(
                        Product(
                            name = nameState,
                            price = price,
                            image = urlState,
                            description = descriptionState,
                            categories = categories
                        )
                    )
                },
                Modifier.fillMaxWidth()
            ) {
                Text("Salvar")
            }
        }
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
    FormVideoScreen(imageUrl = "a")
}