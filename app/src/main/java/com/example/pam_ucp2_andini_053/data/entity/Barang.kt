package com.example.pam_ucp2_andini_053.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class Barang(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val deskripsi: String,
    val harga: String,
    val stok: String,
    val namaSuplier: String,
)
