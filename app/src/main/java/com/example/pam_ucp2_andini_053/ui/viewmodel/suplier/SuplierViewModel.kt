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
