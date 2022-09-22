package br.com.alura.aluvery.dao

import android.util.Log
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleProducts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ProductDao {

    companion object {
        private val dataset = sampleProducts.toMutableList()
        private val productsFlow = MutableStateFlow<List<Product>>(dataset.toList())
    }

    val products: Flow<List<Product>> = productsFlow

    suspend fun save(product: Product) {
        dataset.indexOfFirst {
            product.id == it.id
        }.let { index ->
            if(index > -1) {
                dataset[index] = product
            } else {
                dataset.add(product)
            }
        }
        Log.i("ProductDao", "save: $dataset")
        productsFlow.emit(dataset.toList())
    }

}