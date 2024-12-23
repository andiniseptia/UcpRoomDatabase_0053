package com.example.pam_ucp2_andini_053.ui.viewmodel.suplier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_ucp2_andini_053.data.entity.Suplier
import com.example.pam_ucp2_andini_053.repository.RepositorySpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeSplViewModel (
    private val repositorySpl: RepositorySpl
) : ViewModel() {

    val homeUiStateSpl: StateFlow<HomeUiStateSpl> = repositorySpl.getAllSuplier()
        .filterNotNull()
        .map {
            HomeUiStateSpl (
                listSpl = it.toList(),
                isLoading = false,
            )
        }

}

data class HomeUiStateSpl (
    val listSpl: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)