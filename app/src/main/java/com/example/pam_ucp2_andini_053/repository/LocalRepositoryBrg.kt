package com.example.pam_ucp2_andini_053.repository

import android.util.Log
import com.example.pam_ucp2_andini_053.data.dao.BarangDao
import com.example.pam_ucp2_andini_053.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBrg(
    private val barangDao: BarangDao
) : RepositoryBrg {
    override suspend fun insertBarang(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override fun getAllBarang(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }

    override fun getBarang(id: Int): Flow<Barang> {
        return barangDao.getBarang(id)
    }

    override suspend fun deleteBarang(barang: Barang) {
        barangDao.deleteBarang(barang)
    }

    override suspend fun updateBarang(barang: Barang) {
        barangDao.updateBarang(barang)
    }
}
