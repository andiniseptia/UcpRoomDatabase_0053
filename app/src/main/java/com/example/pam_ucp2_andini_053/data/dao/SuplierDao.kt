package com.example.pam_ucp2_andini_053.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pam_ucp2_andini_053.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SuplierDao {
    @Insert
    suspend fun insertSuplier(suplier: Suplier)

    @Query("SELECT * FROM suplier ORDER BY namaSuplier ASC")
    fun getAllSuplier(): Flow<List<Suplier>>

    @Query("SELECT * FROM suplier WHERE idSuplier = :idSuplier")
    fun getSuplier(idSuplier: Int): Flow<Suplier>
}