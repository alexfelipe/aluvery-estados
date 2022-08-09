package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.User
import br.com.alura.aluvery.ui.theme.AluveryTheme
import kotlinx.coroutines.launch

private val users = listOf(
    User("Alex", "alex1234")
)


class SignInState(
    user: String = "",
    password: String = ""
) {
    var user by mutableStateOf(user)

    var password by mutableStateOf(password)

    fun authenticate() = users.contains(User(user, password))
}

@Composable
fun SignInScreen(
    onSignIn: (String) -> Unit = {},
    onSignUp: () -> Unit = {}
) {
    val state = remember {
        SignInState()
    }
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
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
        Button(
            onClick = {
                // TODO add the logic to authenticate user
                onSignIn(state.user)
            },
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(text = "Sign in")
        }
        TextButton(
            onClick = {
                onSignUp()
            },
            Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Sign up")
        }
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    AluveryTheme {
        Surface {
            SignInScreen()
        }
    }
}
