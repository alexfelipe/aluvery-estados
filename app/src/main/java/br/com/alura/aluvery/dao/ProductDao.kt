package br.com.alura.aluvery.dao

import br.com.alura.aluvery.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductDao {

    fun products(): StateFlow<List<Product>> = products

    fun save(product: Product) {
        products.value = products.value + product
    }

    companion object {
        private val products = MutableStateFlow<List<Product>>(emptyList())
    }

}