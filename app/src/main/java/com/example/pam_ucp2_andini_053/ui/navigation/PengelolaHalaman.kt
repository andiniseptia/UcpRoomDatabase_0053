package com.example.pam_ucp2_andini_053.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pam_ucp2_andini_053.ui.view.barang.DestinasiInsert
import com.example.pam_ucp2_andini_053.ui.view.barang.DetailBrgView
import com.example.pam_ucp2_andini_053.ui.view.toko.HomeTokoView
import com.example.pam_ucp2_andini_053.ui.view.barang.HomeBrgView
import com.example.pam_ucp2_andini_053.ui.view.barang.UpdateBrgView
import com.example.pam_ucp2_andini_053.ui.view.suplier.DestinasiInsertSpl
import com.example.pam_ucp2_andini_053.ui.view.suplier.HomeSplView
import com.example.pam_ucp2_andini_053.ui.view.barang.insertBrgView
import com.example.pam_ucp2_andini_053.ui.view.suplier.InsertSplView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    ) {
        composable(
            route = DestinasiHome.route
        ) {
            HomeTokoView(
                onNavigateToBarang = {
                    navController.navigate(DestinasiHomeBarang.route)
                },
                onNavigateToSuplier = {
                    navController.navigate(DestinasiHomeSuplier.route)
                }
            )
        }


    }
}
