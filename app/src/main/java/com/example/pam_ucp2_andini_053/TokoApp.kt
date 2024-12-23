package com.example.pam_ucp2_andini_053

import android.app.Application
import com.example.pam_ucp2_andini_053.dependenciesinjection.ContainerApp

class TokoApp : Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}