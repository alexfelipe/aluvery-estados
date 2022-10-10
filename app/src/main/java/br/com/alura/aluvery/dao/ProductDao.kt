package br.com.alura.aluvery.dao

import br.com.alura.aluvery.sampledata.sampleProducts

class ProductDao {

    companion object {
        private val products = sampleProducts.toMutableList()
    }

    fun products() = products.toList()

}