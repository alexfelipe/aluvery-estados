package br.com.alura.aluvery.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.MappedProducts
import br.com.alura.aluvery.sampledata.sampleMappedProducts
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.components.ProductsSection
import br.com.alura.aluvery.ui.components.SearchTextField
import br.com.alura.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    sections: List<MappedProducts> = sampleMappedProducts
) {
    var searchText by remember {
        mutableStateOf("")
    }
    HomeScreen(
        sections = sections,
        modifier,
        value = searchText,
        onValueChange = {
            searchText = it
        }
    )
}

@Composable
fun HomeScreen(
    sections: List<MappedProducts>,
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
) {
    Column(modifier) {
        SearchTextField(value, onValueChange = onValueChange)
        if (value.isNotBlank()) {
            LazyColumn(
                Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(sampleProducts.filter {
                    it.name.contains(value, true) ||
                            it.description.contains(value, true)
                }) { product ->
                    CardProductItem(
                        product = product,
                        Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        } else {
            LazyColumn(
                Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(sections) { section ->
                    ProductsSection(
                        title = section.title,
                        products = section.products
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenWithSearchTextPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(
                sections = sampleMappedProducts,
                value = "a"
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "HomeScreenDarkPreview"
)
@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(
                sampleMappedProducts
            ) {

            }
        }
    }
}