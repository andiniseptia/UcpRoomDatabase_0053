package com.example.pam_ucp2_andini_053.repository

import com.example.pam_ucp2_andini_053.data.dao.SuplierDao
import com.example.pam_ucp2_andini_053.data.entity.Barang
import com.example.pam_ucp2_andini_053.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySpl (
    private val suplierDao: SuplierDao
) : RepositorySpl {

}