package br.com.alura.aluvery.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.MappedProducts
import br.com.alura.aluvery.sampledata.sampleMappedProducts
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.components.ProductsSection
import br.com.alura.aluvery.ui.components.SearchTextField

@Composable
fun HomeScreen(
    mappedProducts: List<MappedProducts>,
    desiredProduct: String,
    onDesiredTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        val state = rememberLazyListState()
        val showSearchTextField by remember {
            derivedStateOf {
                state.firstVisibleItemIndex == 0
                        || !state.isScrollInProgress
            }
        }
        AnimatedVisibility(visible = desiredProduct.isNotBlank()) {
            val products = sampleProducts.filter {
                it.name.toUpperCase(Locale.current)
                    .contains(desiredProduct.toUpperCase(Locale.current))
                        || it.description.toUpperCase(Locale.current)
                    .contains(desiredProduct.toUpperCase(Locale.current))
            }
            LazyColumn(
                Modifier
                    .fillMaxSize(),
                state = state,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(72.dp))
                }
                items(products) { product ->
                    var showDescription by remember {
                        mutableStateOf(false)
                    }
                    CardProductItem(
                        product,
                        Modifier
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        showDescription = showDescription,
                        onClick = {
                            showDescription = !showDescription
                        }
                    )
                }
            }
        }
        AnimatedVisibility(visible = desiredProduct.isBlank()) {
            LazyColumn(
                Modifier
                    .fillMaxSize(),
                state = state,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(72.dp))
                }
                items(mappedProducts) { mappedProduct ->
                    ProductsSection(
                        title = mappedProduct.title,
                        products = mappedProduct.products
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = showSearchTextField,
            enter = expandVertically(
                animationSpec = tween(delayMillis = 500)
            ),
            exit = slideOutVertically(
                animationSpec = tween(200)
            )
        ) {
            SearchTextField(
                value = desiredProduct,
                onValueChange = onDesiredTextChange,
                Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        sampleMappedProducts,
        desiredProduct = "",
        onDesiredTextChange = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenWithDesiredTextPreview() {
    HomeScreen(
        sampleMappedProducts,
        desiredProduct = "a",
        onDesiredTextChange = {}
    )
}