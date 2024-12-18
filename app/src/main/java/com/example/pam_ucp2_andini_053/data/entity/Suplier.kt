package com.example.pam_ucp2_andini_053.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suplier")
data class Suplier(
    @PrimaryKey
    val idSuplier: String,
    val namaSuplier: String,
    val kontak: String,
    val alamat: String
)
