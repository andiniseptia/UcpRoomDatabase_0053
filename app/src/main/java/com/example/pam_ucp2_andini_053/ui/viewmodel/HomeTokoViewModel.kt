package com.example.pam_ucp2_andini_053.ui.viewmodel.toko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


data class HomeTokoUiState(
    val navigateToBarang: Boolean = false,
    val navigateToSuplier: Boolean = false
)
