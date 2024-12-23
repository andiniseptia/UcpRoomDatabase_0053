package com.example.pam_ucp2_andini_053.ui.view.barang

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_ucp2_andini_053.R
import com.example.pam_ucp2_andini_053.data.entity.Barang
import com.example.pam_ucp2_andini_053.ui.viewmodel.barang.BarangViewModel
import com.example.pam_ucp2_andini_053.ui.viewmodel.barang.HomeUiStateBrg
import kotlinx.coroutines.launch
import com.example.pam_ucp2_andini_053.ui.customwidget.TopAppBar
import com.example.pam_ucp2_andini_053.ui.navigation.AlamatNavigasi
import com.example.pam_ucp2_andini_053.ui.viewmodel.PenyediaViewModel
import com.example.pam_ucp2_andini_053.ui.viewmodel.barang.HomeBrgViewModel


@Composable
fun HomeBrgView(
    viewModel: HomeBrgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddBrg: () -> Unit = { },
    onBack: () -> Unit = { },
    onDetailClick: (Int) -> Unit = { },
    modifier: Modifier = Modifier
) {
    Scaffold(
       topBar = {
           TopAppBar(
               judul = "Daftar Barang",
               showBackButton = true,
               onBack = onBack,
               modifier = modifier
           )
       },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddBrg,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp),
                containerColor = colorResource(id = R.color.primary),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Barang"
                )
            }
        }
    ) { innerPadding ->
        val homeUiStateBrg = viewModel.homeUiStateBrg.collectAsState().value

        BodyHomeBrgView(
            homeUiStateBrg = homeUiStateBrg,
            onClick = { onDetailClick(it) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun BodyHomeBrgView(
    homeUiStateBrg: HomeUiStateBrg,
    onClick: (Int) -> Unit = { },
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    when {
        homeUiStateBrg.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiStateBrg.isError -> {
            LaunchedEffect(homeUiStateBrg.errorMessage) {
                homeUiStateBrg.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeUiStateBrg.listBrg.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data barang.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            ListBarang(
                listBrg = homeUiStateBrg.listBrg,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListBarang(
    listBrg: List<Barang>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = { }
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listBrg,
            itemContent = { brg ->
                CardBrg(
                    brg = brg,
                    onClick = { onClick(brg.id) }
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardBrg(
    brg: Barang,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    val cardColor = when {
        brg.stok.toIntOrNull() == 0 -> Color.Gray
        brg.stok.toIntOrNull() in 1 ..10 -> colorResource(id = R.color.card1)
        else -> colorResource(id = R.color.card11)
    }

    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = cardColor),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Cart Icon", tint = Color.White)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = brg.nama,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "Info Icon", tint = Color.White)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = brg.harga,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Info Icon", tint = Color.White)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = brg.stok,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "Home Icon", tint = Color.White)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = brg.namaSuplier,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }


        }
    }
}
