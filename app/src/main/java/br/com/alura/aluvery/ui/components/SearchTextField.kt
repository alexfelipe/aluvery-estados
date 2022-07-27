package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField() {
    var searchedText: MutableState<String> = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = searchedText.value,
        onValueChange = {
            searchedText.value = it
        },
        Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        placeholder = {
            Text("O que você procura?")
        },
        label = {
            Text("Produto")
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(100)
    )
}
