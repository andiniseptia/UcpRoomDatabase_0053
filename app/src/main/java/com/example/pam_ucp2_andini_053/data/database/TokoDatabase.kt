package com.example.pam_ucp2_andini_053.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pam_ucp2_andini_053.data.dao.BarangDao
import com.example.pam_ucp2_andini_053.data.dao.SuplierDao
import com.example.pam_ucp2_andini_053.data.entity.Barang
import com.example.pam_ucp2_andini_053.data.entity.Suplier

@Database(entities = [Barang::class, Suplier::class], version = 1, exportSchema = false)
abstract class TokoDatabase : RoomDatabase() {

    abstract fun barangDao(): BarangDao
    abstract fun suplierDao(): SuplierDao

}