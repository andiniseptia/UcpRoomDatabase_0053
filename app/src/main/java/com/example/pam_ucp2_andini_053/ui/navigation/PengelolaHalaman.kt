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

        composable(
            route = DestinasiHomeBarang.route
        ) {
            HomeBrgView(
                onBack = {
                    navController.popBackStack()
                },
                onAddBrg = {
                    navController.navigate(DestinasiInsert.route)
                },
                onDetailClick = { id ->
                    navController.navigate("${DestinasiDetailBarang.route}/$id")
                },
                modifier = modifier
            )
        }


        composable(
            route = DestinasiInsert.route
        ) {
            insertBrgView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            DestinasiDetailBarang.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBarang.ID) {
                    type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt(DestinasiDetailBarang.ID)
            id?.let { id ->
                DetailBrgView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateBarang.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }

        }

        composable(
            DestinasiUpdateBarang.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateBarang.ID) {
                    type = NavType.IntType
                }
            )
        ) {
            UpdateBrgView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }

        composable(
            route = DestinasiHomeSuplier.route
        ) {
            HomeSplView(
                onBack = {
                    navController.popBackStack()
                },
                onAddSpl = {
                    navController.navigate(DestinasiInsertSpl.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertSpl.route
        ) {
            InsertSplView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}
