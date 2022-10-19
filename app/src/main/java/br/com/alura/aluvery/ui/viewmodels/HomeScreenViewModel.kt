package br.com.alura.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.states.HomeScreenUiState
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    var uiState: HomeScreenUiState by mutableStateOf(
        HomeScreenUiState(
            onSearchChange = {
                uiState = uiState.copy(
                    searchText = it
                )
                uiState = uiState.copy(
                    searchedProducts = searchedProducts()
                )
            },
        )
    )
        private set

    init {
        viewModelScope.launch {
            searchSections()
        }
    }

    private fun searchedProducts() =
        dao.products().value
            .filter(containsInNameOrDescrioption())

    private suspend fun searchSections() {
        dao.products().collect {
            uiState = uiState.copy(
                sections = mapOf("Todos produtos" to it),
                searchedProducts = searchedProducts()
            )
        }
    }

    private fun containsInNameOrDescrioption() = { product: Product ->
        product.name.contains(
            uiState.searchText,
            ignoreCase = true,
        ) || product.description?.contains(
            uiState.searchText,
            ignoreCase = true,
        ) ?: false
    }

}