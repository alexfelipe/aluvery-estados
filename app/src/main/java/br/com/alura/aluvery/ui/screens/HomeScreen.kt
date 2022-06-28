package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.ui.components.ProductsSection
import java.math.BigDecimal

@Composable
fun HomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(Modifier)
        ProductsSection("Promoções", sampleProducts)
        ProductsSection(
            "Doces", listOf(
                Product(
                    name = "Chocolate",
                    price = BigDecimal("5.99"),
                    image = R.drawable.chocolate
                )
            )
        )
        ProductsSection(
            "Bebidas", listOf(
                Product(
                    name = "Cerveja",
                    price = BigDecimal("9.99"),
                    image = R.drawable.beer
                ),
                Product(
                    name = "Refrigerante",
                    price = BigDecimal("3.99"),
                    image = R.drawable.soda
                ),

                )
        )
        Spacer(Modifier)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}