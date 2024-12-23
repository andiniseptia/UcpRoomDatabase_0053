package com.example.pam_ucp2_andini_053.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_ucp2_andini_053.data.NamaSuplier
import com.example.pam_ucp2_andini_053.data.entity.Barang
import com.example.pam_ucp2_andini_053.repository.RepositoryBrg
import kotlinx.coroutines.launch

class BarangViewModel (private val repositoryBrg: RepositoryBrg) : ViewModel() {

    private val suplier = NamaSuplier.suplier

    var uiState by mutableStateOf(BrgUIState())

    fun updateState(barangEvent: BarangEvent) {
        uiState = uiState.copy(
            barangEvent = barangEvent,
        )
    }

    private fun validateFields(): Boolean {
        val event = uiState.barangEvent
        val errorState = FormErrorStateBrg (
            nama = if (event.nama.isNotEmpty()) null else "Nama Barang tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok tidak boleh kosong",
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Nama Suplier tidak boleh kosong"
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }


}

data class BrgUIState (
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorStateBrg = FormErrorStateBrg(),
    val snackBarMessage: String? = null,
)

fun BarangEvent.toBarangEntity(): Barang = Barang (
    id = id,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namaSuplier = namaSuplier
)

data class BarangEvent (
    val id: Int = 0,
    val nama: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: String = "",
    val namaSuplier: String = ""
)

data class FormErrorStateBrg (
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaSuplier: String? = null
) {
    fun isValid(): Boolean {
        return nama == null && deskripsi == null &&
                harga == null && stok == null && namaSuplier == null
    }
}