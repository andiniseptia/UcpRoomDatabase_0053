package com.example.pam_ucp2_andini_053.ui.viewmodel.barang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_ucp2_andini_053.data.entity.Barang
import com.example.pam_ucp2_andini_053.repository.RepositoryBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeBrgViewModel (
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {

    val homeUiStateBrg: StateFlow<HomeUiStateBrg> = repositoryBrg.getAllBarang()
        .filterNotNull()
        .map {
            HomeUiStateBrg (
                listBrg = it.toList(),
                isLoading = false,
            )
        }

}

data class HomeUiStateBrg (
    val listBrg: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)