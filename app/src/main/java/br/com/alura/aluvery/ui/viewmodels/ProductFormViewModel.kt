package br.com.alura.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.states.ProductFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormViewModel : ViewModel() {
    private val dao = ProductDao()

    private val _uiState: MutableStateFlow<ProductFormUiState> =
        MutableStateFlow(ProductFormUiState())

    val uiState: StateFlow<ProductFormUiState> get() = _uiState
    private val formatter = DecimalFormat("#.##")

    init {
        _uiState.update { state ->
            state.copy(
                onUrlChange = {
                    _uiState.value = _uiState.value.copy(
                        url = it
                    )
                },
                onNameChange = {
                    _uiState.value = _uiState.value.copy(
                        name = it
                    )
                },
                onDescriptionChange = {
                    _uiState.value = _uiState.value.copy(
                        description = it
                    )
                },
                onPriceChange = {
                    try {
                        _uiState.value = _uiState.value
                            .copy(price = formatter.format(BigDecimal(it)))
                    } catch (e: IllegalArgumentException) {
                        if (it.isEmpty()) {
                            _uiState.value =
                                _uiState.value.copy(price = it)
                        }
                    }
                },
                isShowPreview = state.url.isNotEmpty()
            )
        }
    }

    fun save() {
        uiState.value.run {
            val convertedPrice = try {
                BigDecimal(price)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }
            val product = Product(
                name = name,
                image = url,
                price = convertedPrice,
                description = description
            )
            dao.save(product)
        }
    }
}
