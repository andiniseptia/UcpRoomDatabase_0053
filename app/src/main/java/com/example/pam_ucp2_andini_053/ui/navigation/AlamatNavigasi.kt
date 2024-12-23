package com.example.pam_ucp2_andini_053.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiHomeBarang : AlamatNavigasi {
    override val route = "homeBrg"
}

object DestinasiDetailBarang : AlamatNavigasi {
    override val route = "detail"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}" // Rute ini membutuhkan argumen ID
}

object DestinasiUpdateBarang : AlamatNavigasi {
    override val route = "update"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}" // Rute ini membutuhkan argumen ID
}


