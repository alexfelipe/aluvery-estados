package br.com.alura.aluvery.dao

import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductDao {

    fun products(): StateFlow<List<Product>> = products

    fun save(product: Product) {
        products.value = listOf(product) + products.value
    }

    companion object {
        private val products = MutableStateFlow(sampleProducts)
    }

}