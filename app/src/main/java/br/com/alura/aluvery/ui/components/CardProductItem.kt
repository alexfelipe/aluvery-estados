package br.com.alura.aluvery.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.extensions.toBrazilianCurrency
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleProducts
import coil.compose.AsyncImage
import kotlin.random.Random

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    showDescription: Boolean = false,
    onClick: () -> Unit = {},
    elevation: Dp = 4.dp
) {
    Card(
        modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .clickable(onClick = onClick),
        elevation = elevation
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondary)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name,
                    color = MaterialTheme.colors.onSecondary
                )
                Text(
                    text = product.price.toBrazilianCurrency(),
                    color = MaterialTheme.colors.onSecondary
                )
            }
            AnimatedVisibility(visible = showDescription) {
                if (product.description.isNotBlank()) {
                    Text(
                        text = product.description,
                        Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardProductItemPreview() {
    CardProductItem(
        product = sampleProducts[Random.nextInt(sampleProducts.size)],
    )
}

@Preview
@Composable
fun ExpandedCardProductItemPreview() {
    var showDescription by remember {
        mutableStateOf(true)
    }
    CardProductItem(
        product = sampleProducts[Random.nextInt(sampleProducts.size)],
        showDescription = true,
        onClick = {
            showDescription = !showDescription
        }
    )
}