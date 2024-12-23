package com.example.pam_ucp2_andini_053.repository

import android.util.Log
import com.example.pam_ucp2_andini_053.data.dao.BarangDao
import com.example.pam_ucp2_andini_053.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBrg(
    private val barangDao: BarangDao
) : RepositoryBrg {

}
