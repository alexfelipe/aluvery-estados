package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.MappedProducts
import br.com.alura.aluvery.sampledata.sampleMappedProducts
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.ui.components.CardProductItem

@Composable
fun HomeScreen(
    sections: List<MappedProducts>
) {
    Column {
        var state: MutableState<String> = remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = state.value,
            onValueChange = {
                state.value = it
            },
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text("O que você procura?")
            },
            label = {
                Text("Produto")
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )
            },
            shape = RoundedCornerShape(100)
        )
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(sampleProducts) { product ->
                CardProductItem(
                    product = product,
                    Modifier.padding(horizontal = 16.dp)
                )
            }
        }
//        LazyColumn(
//            Modifier
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            contentPadding = PaddingValues(vertical = 16.dp)
//        ) {
//            items(sections) { section ->
//                ProductsSection(
//                    title = section.title,
//                    products = section.products
//                )
//            }
//        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        sampleMappedProducts
    )
}