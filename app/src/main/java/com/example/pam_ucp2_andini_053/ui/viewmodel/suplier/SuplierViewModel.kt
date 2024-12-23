package com.example.pam_ucp2_andini_053.ui.viewmodel.suplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_ucp2_andini_053.data.entity.Suplier
import com.example.pam_ucp2_andini_053.repository.RepositorySpl
import kotlinx.coroutines.launch
import java.lang.Exception

class SuplierViewModel (private val repositorySpl: RepositorySpl) : ViewModel() {
    var uiState by mutableStateOf(SplUIState())

    fun updateState(suplierEvent: SuplierEvent) {
        uiState = uiState.copy(
            suplierEvent = suplierEvent,
        )
    }

    private fun validateFields(): Boolean {
        val event = uiState.suplierEvent
        val errorState = FormErrorStateSpl (
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Nama tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "Kontak tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong"
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.suplierEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorySpl.insertSuplier(currentEvent.toSuplierEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        suplierEvent = SuplierEvent(),
                        isEntryValid = FormErrorStateSpl()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data anda."
            )
        }
    }

    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }

}

data class SplUIState (
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val isEntryValid: FormErrorStateSpl = FormErrorStateSpl(),
    val snackBarMessage: String? = null,
)

fun SuplierEvent.toSuplierEntity(): Suplier = Suplier (
    namaSuplier = namaSuplier,
    kontak = kontak,
    alamat = alamat
)

data class SuplierEvent (
    val id: Int = 0,
    val namaSuplier: String = "",
    val kontak: String = "",
    val alamat: String = ""
)

data class  FormErrorStateSpl (
    val namaSuplier: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
) {
    fun isValid(): Boolean {
        return namaSuplier == null &&
                kontak == null && alamat == null
    }
}
