package com.example.pam_ucp2_andini_053.ui.viewmodel.barang

import android.util.Log
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_ucp2_andini_053.data.NamaSuplier
import com.example.pam_ucp2_andini_053.data.entity.Barang
import com.example.pam_ucp2_andini_053.repository.RepositoryBrg
import com.example.pam_ucp2_andini_053.ui.navigation.DestinasiUpdateBarang
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateBrgViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {

    private val suplier = NamaSuplier.suplier

    var updateUIState by mutableStateOf(BrgUIState())
        private set

    private val _id: Int = checkNotNull(savedStateHandle[DestinasiUpdateBarang.ID])

    init {
        viewModelScope.launch {
            updateUIState = repositoryBrg.getBarang(_id)
                .filterNotNull()
                .first()
                .toUIStateBrg()
        }
    }

    fun updateState(barangEvent: BarangEvent) {
        updateUIState = updateUIState.copy(
            barangEvent = barangEvent,
        )
    }


}

fun Barang.toUIStateBrg(): BrgUIState = BrgUIState(
    barangEvent = this.toDetailUiEvent(),
)