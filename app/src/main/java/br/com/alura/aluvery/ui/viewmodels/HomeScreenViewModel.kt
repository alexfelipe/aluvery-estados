package br.com.alura.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.alura.aluvery.ui.screens.HomeScreenUiState

class HomeScreenViewModel : ViewModel() {

    var uiState by mutableStateOf(HomeScreenUiState())
        private set

}