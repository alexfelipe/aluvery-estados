package br.com.alura.aluvery.sampledata

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.alura.aluvery.model.Category
import br.com.alura.aluvery.model.Product
import java.math.BigDecimal

val sampleCandies = listOf(
        Product(
                name = "Chocolate",
                price = BigDecimal("3.99"),
                image = "https://images.pexels.com/photos/65882/chocolate-dark-coffee-confiserie-65882.jpeg",
                categories = listOf(Category.Candies)
        ),
        Product(
                name = "Sorvete",
                price = BigDecimal("5.99"),
                image = "https://images.pexels.com/photos/1352278/pexels-photo-1352278.jpeg",
                description = LoremIpsum(5).values.first(),
                categories = listOf(Category.Candies)
        ),
        Product(
                name = "Bolo",
                price = BigDecimal("11.99"),
                image = "https://images.pexels.com/photos/291528/pexels-photo-291528.jpeg",
                categories = listOf(Category.Candies, Category.Promotions)
        )
)

val sampleDrinks = listOf(
        Product(
                name = "Cerveja",
                price = BigDecimal("5.99"),
                image = "https://images.pexels.com/photos/1552630/pexels-photo-1552630.jpeg",
                description = LoremIpsum(20).values.first(),
                categories = listOf(Category.Drinks)
        ),
        Product(
                name = "Refrigerante",
                price = BigDecimal("4.99"),
                image = "https://images.pexels.com/photos/2775860/pexels-photo-2775860.jpeg",
                categories = listOf(Category.Drinks)
        ),
        Product(
                name = "Suco",
                price = BigDecimal("7.99"),
                image = "https://images.pexels.com/photos/96974/pexels-photo-96974.jpeg",
                description = LoremIpsum(100).values.first(),
                categories = listOf(Category.Drinks, Category.Promotions)
        ),
        Product(
                name = "Água",
                price = BigDecimal("2.99"),
                image = "https://images.pexels.com/photos/327090/pexels-photo-327090.jpeg",
                categories = listOf(Category.Drinks, Category.Promotions)
        )
)

val sampleProducts: List<Product> = listOf(
        Product(
                name = "Hamburguer",
                price = BigDecimal("12.99"),
                image = "https://images.pexels.com/photos/1639557/pexels-photo-1639557.jpeg",
                description = LoremIpsum(20).values.first(),
                categories = listOf(Category.Promotions)
        ),
        Product(
                name = "Pizza",
                price = BigDecimal("19.99"),
                image = "https://images.pexels.com/photos/825661/pexels-photo-825661.jpeg",
                categories = listOf(Category.Promotions)
        ),
        Product(
                name = "Batata frita",
                price = BigDecimal("7.99"),
                image = "https://images.pexels.com/photos/1583884/pexels-photo-1583884.jpeg",
                description = LoremIpsum(50).values.first(),
        ), *sampleDrinks.toTypedArray(), *sampleCandies.toTypedArray()
)

val sampleSections = mapOf(
        "Promoções" to sampleProducts,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
)