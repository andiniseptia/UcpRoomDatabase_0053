package com.example.pam_ucp2_andini_053.dependenciesinjection

import android.content.Context
import com.example.pam_ucp2_andini_053.data.database.TokoDatabase
import com.example.pam_ucp2_andini_053.repository.LocalRepositoryBrg
import com.example.pam_ucp2_andini_053.repository.LocalRepositorySpl
import com.example.pam_ucp2_andini_053.repository.RepositoryBrg
import com.example.pam_ucp2_andini_053.repository.RepositorySpl

interface InterfaceContainerApp {
    val repositoryBrg: RepositoryBrg
    val repositorySpl: RepositorySpl
}

