package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .background(Color.White, RoundedCornerShape(32.dp))
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            Modifier
                .fillMaxWidth()
                .height(64.dp),
            placeholder = {
                Text("O que você procura?")
            },
            label = {
                Text("Produtos")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            shape = RoundedCornerShape(32.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White
            )
        )
    }
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    SearchTextField(value = "", onValueChange = {})
}

@Preview
@Composable
fun SearchTextFieldWithTextPreview() {
    SearchTextField(value = "sample", onValueChange = {})
}