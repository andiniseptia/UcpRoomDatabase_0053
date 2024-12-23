package com.example.pam_ucp2_andini_053.repository

import com.example.pam_ucp2_andini_053.data.dao.SuplierDao
import com.example.pam_ucp2_andini_053.data.entity.Barang
import com.example.pam_ucp2_andini_053.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySpl (
    private val suplierDao: SuplierDao
) : RepositorySpl {
    override suspend fun insertSuplier(suplier: Suplier) {
        suplierDao.insertSuplier(suplier)
    }

    override fun getAllSuplier(): Flow<List<Suplier>> {
        return suplierDao.getAllSuplier()
    }

    override fun getSuplier(idSuplier: Int): Flow<Suplier> {
        return suplierDao.getSuplier(idSuplier)
    }

}