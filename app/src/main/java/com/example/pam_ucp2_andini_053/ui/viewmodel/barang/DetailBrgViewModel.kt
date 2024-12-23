package com.example.pam_ucp2_andini_053.ui.viewmodel.barang

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_ucp2_andini_053.data.entity.Barang
import com.example.pam_ucp2_andini_053.repository.RepositoryBrg
import com.example.pam_ucp2_andini_053.ui.navigation.DestinasiDetailBarang
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailBrgViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,
) : ViewModel() {
    private val _id: Int = checkNotNull(savedStateHandle[DestinasiDetailBarang.ID])

    val detailUiState: StateFlow<DetailUiState> = repositoryBrg.getBarang(_id)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true,
            ),
        )

    fun deleteBrg() {
        detailUiState.value.detailUiEvent.toBarangEntity().let {
            viewModelScope.launch {
                repositoryBrg.deleteBarang(it)
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError:Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == BarangEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != BarangEvent()
}

fun Barang.toDetailUiEvent(): BarangEvent {
    return BarangEvent(
        id = id,
        nama = nama,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        namaSuplier = namaSuplier
    )
}
