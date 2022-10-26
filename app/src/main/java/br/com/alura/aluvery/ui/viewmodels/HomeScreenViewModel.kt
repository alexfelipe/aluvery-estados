package br.com.alura.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.ui.screens.HomeScreenUiState
import java.math.BigDecimal

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    var uiState: HomeScreenUiState by mutableStateOf(
        HomeScreenUiState(
            sections = mapOf(
                "Todos produtos" to dao.products(),
                "Promoções" to sampleDrinks + sampleCandies,
                "Doces" to sampleCandies,
                "Bebidas" to sampleDrinks
            ),
            onSearchChange = {
                val searchedProducts = searchedProducts(it)
                uiState = uiState.copy(
                    searchText = it,
                    searchedProducts = searchedProducts
                )
            }
        ))
        private set

    private fun containsInNameOrDescrioption(text: String) = { product: Product ->
        product.name.contains(
            text,
            ignoreCase = true,
        ) || product.description?.contains(
            text,
            ignoreCase = true,
        ) ?: false
    }

    private fun searchedProducts(text: String) =
        if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescrioption(text)) +
                    dao.products().filter(containsInNameOrDescrioption(text))
        } else emptyList()

    fun findProducts() {
        uiState = uiState.copy(sections = mapOf(
            "Todos produtos" to listOf(sampleProducts.random()),
            "Promoções" to sampleDrinks + sampleCandies,
            "Doces" to sampleCandies,
            "Bebidas" to sampleDrinks
        ))
    }

}