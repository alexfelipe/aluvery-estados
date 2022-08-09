package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.lang.Exception

class SignUpState(

) {
    var user by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    fun signUp() {

        if (password != confirmPassword) {
            throw IllegalStateException("not same password")
        }
    }

}

@Composable
fun SignUpScreen() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    SnackbarHost(hostState = snackbarHostState)
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val state = remember {
            SignUpState()
        }
        TextField(
            value = state.user,
            onValueChange = {
                state.user = it
            },
            Modifier
                .padding(
                    start = 8.dp,
                    top = 8.dp,
                    end = 8.dp
                )
                .fillMaxWidth(),
            placeholder = {
                Text(text = "User")
            }
        )
        TextField(
            value = state.password,
            onValueChange = {
                state.password = it
            },
            Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Password")
            }
        )
        TextField(
            value = state.confirmPassword,
            onValueChange = {
                state.confirmPassword = it
            },
            Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Confirm Password")
            }
        )
        Button(
            onClick = {
                try {
                    state.signUp()
                } catch (e: Exception) {
                    e.message?.let {
                        scope.launch {
                            snackbarHostState.showSnackbar(it)
                        }
                    }
                }
            },
            Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Sign Up")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}