package com.example.pam_ucp2_andini_053.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pam_ucp2_andini_053.TokoApp
import com.example.pam_ucp2_andini_053.ui.viewmodel.barang.BarangViewModel
import com.example.pam_ucp2_andini_053.ui.viewmodel.barang.DetailBrgViewModel
import com.example.pam_ucp2_andini_053.ui.viewmodel.barang.HomeBrgViewModel
import com.example.pam_ucp2_andini_053.ui.viewmodel.barang.UpdateBrgViewModel
import com.example.pam_ucp2_andini_053.ui.viewmodel.suplier.HomeSplViewModel
import com.example.pam_ucp2_andini_053.ui.viewmodel.suplier.SuplierViewModel

object PenyediaViewModel {


}

fun CreationExtras.tokoApp(): TokoApp = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TokoApp)