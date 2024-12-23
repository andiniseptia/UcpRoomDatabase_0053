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