package br.com.alura.aluvery.dao

import br.com.alura.aluvery.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductDao {

    val products get() = Companion.products.asStateFlow()

    fun save(product: Product) {
        Companion.products.update {
            it + product
        }
    }

    companion object {
        private val products = MutableStateFlow<List<Product>>(emptyList())
    }

}