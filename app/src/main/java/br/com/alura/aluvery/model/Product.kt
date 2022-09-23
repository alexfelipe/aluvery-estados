package br.com.alura.aluvery.model

import java.math.BigDecimal
import java.util.*

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val price: BigDecimal,
    val image: String? = null,
    val description: String? = null,
    val categories: List<Category> = emptyList()
)
