package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ProfileScreen(onClick: () -> Unit = {}) {
    Column {
        Text(text = "Profile screen - implementation wip")
        Button(onClick = onClick) {
            Text(text = "SignIn screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}