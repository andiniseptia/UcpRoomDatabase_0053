package com.example.pam_ucp2_andini_053.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suplier")
data class Suplier(
    @PrimaryKey(autoGenerate = true)
    val idSuplier: Int = 0,
    val namaSuplier: String,
    val kontak: String,
    val alamat: String
)
