package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.User
import br.com.alura.aluvery.ui.theme.AluveryTheme
import kotlinx.coroutines.launch

class AuthenticationState(
    user: String = "",
    password: String = ""
) {

    var user by mutableStateOf(user)
    var password by mutableStateOf(password)

    fun authenticate() = users.contains(User(user, password))

}

@Composable
fun AuthenticationScreen() {
    val state = remember {
        AuthenticationState()
    }
    val scope = rememberCoroutineScope()
    val snackbarState = remember {
        SnackbarHostState()
    }
    SnackbarHost(hostState = snackbarState)
    Column(Modifier.fillMaxSize()) {
        TextField(
            value = state.user,
            onValueChange = {
                state.user = it
            }, Modifier
                .padding(8.dp)
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
                .padding(8.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Password")
            }
        )
        Button(
            onClick = {
                val message = if (state.authenticate()) {
                    "authenticated"
                } else "not authenticate"
                scope.launch {
                    snackbarState.showSnackbar(message)
                }
            },
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(text = "Sign in")
        }
        TextButton(
            onClick = {
                scope.launch {
                    snackbarState.showSnackbar("sign up")
                }
            },
            Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Sign up")
        }
    }
}

private val users = listOf<User>(
    User("Alex", "alex1234")
)

@Preview
@Composable
fun AuthenticationScreenPreview() {
    AluveryTheme {
        Surface {
            AuthenticationScreen()
        }
    }
}