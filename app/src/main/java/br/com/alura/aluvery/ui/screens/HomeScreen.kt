package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Category
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.components.ProductsSection
import br.com.alura.aluvery.ui.components.SearchTextField
import br.com.alura.aluvery.ui.theme.AluveryTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

@Composable
fun HomeScreen(state: HomeScreenState) {
    val sections by state.sections
        .collectAsState(initial = emptyMap())
    var text by remember {
        mutableStateOf("")
    }
    val products by state.filterProducts(text)
        .collectAsState(initial = emptyList())
    HomeScreen(
        sections = sections,
        searchedProducts = products,
        searchText = text
    ) {
        text = it
    }
}

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    searchedProducts: List<Product>,
    modifier: Modifier = Modifier,
    searchText: String = "",
    onSearchTextChange: (String) -> Unit = {}
) {
    Column(modifier) {
        SearchTextField(
            searchText = searchText,
            onSearchChange = onSearchTextChange,
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (searchText.isBlank()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(
                            title = title,
                            products = products
                        )
                    }
                }
            } else {
                items(searchedProducts) { p ->
                    CardProductItem(
                        product = p,
                        Modifier.padding(horizontal = 16.dp),
                    )
                }
            }
        }
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (searchText.isBlank()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(
                            title = title,
                            products = products
                        )
                    }
                }
            } else {
                items(searchedProducts) { p ->
                    CardProductItem(
                        product = p,
                        Modifier.padding(horizontal = 16.dp),
                    )
                }
            }
        }
    }
}

class HomeScreenState(
    private val dao: ProductDao = ProductDao()
) {

    private fun products() = dao.products

    val sections = flow {
        products().collect { products ->
            val sections: Map<String, List<Product>> =
                if (products.isEmpty()) {
                    emptyMap()
                } else {
                    mutableMapOf("Todos produtos" to products) +
                            sectionsByCategory(products)
                }
            emit(sections)
        }
    }

    private fun sectionsByCategory(products: List<Product>) =
        Category.values().associate { category ->
            val filteredProducts = products
                .filter { product ->
                    product.categories.contains(
                        category
                    )
                }
            category.label to filteredProducts
        }.filterValues {
            it.isNotEmpty()
        }

    fun filterProducts(text: String) = flow {
        if (text.isNotBlank()) {
            val filteredProducts = products().first().filter { products ->
                products.name.contains(
                    text,
                    ignoreCase = true,
                ) || products.description?.contains(
                    text,
                    ignoreCase = true,
                ) ?: false
            }
            emit(filteredProducts)
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(
                sections = sampleSections,
                searchedProducts = sampleProducts
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenWithSearchTextPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(
                sections = sampleSections,
                searchedProducts = sampleProducts,
                searchText = "a",
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenWithEmptyContent() {
    AluveryTheme {
        Surface {
            HomeScreen(
                sections = emptyMap(),
                searchedProducts = listOf(),
                searchText = ""
            )
        }
    }
}