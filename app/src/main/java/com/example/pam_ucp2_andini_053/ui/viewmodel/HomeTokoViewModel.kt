package com.example.pam_ucp2_andini_053.ui.viewmodel.toko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeTokoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeTokoUiState())
    val uiState: StateFlow<HomeTokoUiState> = _uiState


}

data class HomeTokoUiState(
    val navigateToBarang: Boolean = false,
    val navigateToSuplier: Boolean = false
)
