package br.com.alura.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.states.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    private var _uiState: MutableStateFlow<HomeScreenUiState> =
        MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> get() = _uiState

    init {
        viewModelScope.launch {
            searchSections()
        }
        _uiState.update { uiState ->
            uiState.copy(
                onSearchChange = {
                    _uiState.value = _uiState.value.copy(
                        searchText = it
                    )
                    _uiState.value = _uiState.value.copy(
                        searchedProducts = searchedProducts()
                    )
                }
            )
        }
    }

    private fun searchedProducts() =
        dao.products().value
            .filter(containsInNameOrDescrioption())

    private suspend fun searchSections() {
        dao.products().collect {
            _uiState.value = _uiState.value.copy(
                sections = mapOf("Todos produtos" to it),
                searchedProducts = searchedProducts()
            )
        }
    }

    private fun containsInNameOrDescrioption() = { product: Product ->
        product.name.contains(
            _uiState.value.searchText,
            ignoreCase = true,
        ) || product.description?.contains(
            _uiState.value.searchText,
            ignoreCase = true,
        ) ?: false
    }

}