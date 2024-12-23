package com.example.pam_ucp2_andini_053.repository

import com.example.pam_ucp2_andini_053.data.entity.Barang
import kotlinx.coroutines.flow.Flow

interface RepositoryBrg {
    suspend fun insertBarang(barang: Barang)

    fun getAllBarang(): Flow<List<Barang>>

    fun getBarang(id: Int): Flow<Barang>

    suspend fun deleteBarang(barang: Barang)

    suspend fun updateBarang(barang: Barang)
}