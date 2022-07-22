package br.com.alura.aluvery.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.MappedProducts
import br.com.alura.aluvery.sampledata.sampleMappedProducts
import br.com.alura.aluvery.ui.components.ProductsSection
import kotlin.random.Random

@Composable
fun HomeScreen(
    sections: List<MappedProducts>
) {
    Column {
        var state: MutableState<String> = remember {
            mutableStateOf("${Random.nextInt()}")
        }
        TextField(value = state.value, onValueChange = { newValue ->
            Log.i("HomeScreen", "textField: $newValue")
            Log.i("HomeScreen", "value: ${state.value}")
            state.value = newValue
        })
        LazyColumn(
                Modifier
                        .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(sections) { section ->
                ProductsSection(
                        title = section . title,
                        products = section.products
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        sampleMappedProducts
    )
}