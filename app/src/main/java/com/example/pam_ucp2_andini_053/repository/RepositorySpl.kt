package com.example.pam_ucp2_andini_053.repository

import com.example.pam_ucp2_andini_053.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpl {
    suspend fun insertSuplier(suplier: Suplier)

    fun getAllSuplier(): Flow<List<Suplier>>

    fun getSuplier(idSuplier: Int): Flow<Suplier>
}