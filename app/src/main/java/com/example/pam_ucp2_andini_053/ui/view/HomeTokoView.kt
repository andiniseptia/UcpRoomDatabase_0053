package com.example.pam_ucp2_andini_053.ui.view.toko

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_ucp2_andini_053.R
import com.example.pam_ucp2_andini_053.ui.customwidget.TopAppBarHome
import com.example.pam_ucp2_andini_053.ui.viewmodel.toko.HomeTokoViewModel

@Preview(showBackground = true)
@Composable
fun HomeTokoView(
    viewModel: HomeTokoViewModel = viewModel(),
    onNavigateToBarang: () -> Unit = {},
    onNavigateToSuplier: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBarHome()
        },
        modifier = modifier
    )

    {

        innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Selamat Datang di Toko",
                    modifier = Modifier.padding(30.dp),
                    color = colorResource(id = R.color.primary),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )

                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.primary)
                    ),
                    onClick = { viewModel.navigateToBarang() },

                ) {
                    Text(

                        text = "Barang")
                }

                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.primary)),
                    onClick = { viewModel.navigateToSuplier() }
                ) {
                    Text(text = "Suplier")
                }
            }
        }
    }

    LaunchedEffect(uiState.navigateToBarang) {
        if (uiState.navigateToBarang) {
            viewModel.resetNavigation()
            onNavigateToBarang()
        }
    }

    LaunchedEffect(uiState.navigateToSuplier) {
        if (uiState.navigateToSuplier) {
            viewModel.resetNavigation()
            onNavigateToSuplier()
        }
    }
}

