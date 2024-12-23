package com.example.pam_ucp2_andini_053.ui.viewmodel.toko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeTokoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeTokoUiState())
    val uiState: StateFlow<HomeTokoUiState> = _uiState

    fun navigateToBarang() {
        _uiState.update { it.copy(navigateToBarang = true, navigateToSuplier = false) }
    }

    fun navigateToSuplier() {
        _uiState.update { it.copy(navigateToBarang = false, navigateToSuplier = true) }
    }

    fun resetNavigation() {
        _uiState.update { it.copy(navigateToBarang = false, navigateToSuplier = false) }
    }
}

data class HomeTokoUiState(
    val navigateToBarang: Boolean = false,
    val navigateToSuplier: Boolean = false
)
