package com.example.pam_ucp2_andini_053.ui.view.barang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_ucp2_andini_053.R
import com.example.pam_ucp2_andini_053.data.NamaSuplier
import com.example.pam_ucp2_andini_053.repository.RepositoryBrg
import com.example.pam_ucp2_andini_053.ui.navigation.AlamatNavigasi
import com.example.pam_ucp2_andini_053.ui.viewmodel.barang.BarangEvent
import com.example.pam_ucp2_andini_053.ui.viewmodel.barang.BarangViewModel
import com.example.pam_ucp2_andini_053.ui.viewmodel.barang.BrgUIState
import kotlinx.coroutines.launch
import java.text.Normalizer.Form
import com.example.pam_ucp2_andini_053.ui.customwidget.TopAppBar
import com.example.pam_ucp2_andini_053.ui.viewmodel.PenyediaViewModel
import com.example.pam_ucp2_andini_053.ui.viewmodel.barang.FormErrorStateBrg

object DestinasiInsert : AlamatNavigasi {
    override val route: String = "insert_brg"
}


@Composable
fun insertBrgView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BarangViewModel = viewModel(factory = PenyediaViewModel.Factory),

) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect (uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold (
        topBar = {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Barang",
                modifier = modifier
            )
        },
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column (
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            InsertBodyBrg(
                uiState = uiState,
                onValueChange = { updatedEvent ->
                    viewModel.updateState(updatedEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodyBrg(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    uiState: BrgUIState,
    onClick: () -> Unit
) {
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FormBarang(
            barangEvent = uiState.barangEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.primary)
            ),
        ) {
            Text("Simpan")
        }
    }
}


@Composable
fun FormBarang(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = {},
    errorState: FormErrorStateBrg = FormErrorStateBrg(),
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = {
                onValueChange(barangEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan nama") }
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = { Text("Deskripsi") },
            isError = errorState.deskripsi != null,
            placeholder = { Text("Masukkan Deskripsi") }
        )
        Text(
            text = errorState.deskripsi ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = {
                onValueChange(barangEvent.copy(harga = it))
            },
            label = { Text("Harga") },
            isError = errorState.harga != null,
            placeholder = { Text("Masukkan Harga") }
        )
        Text(
            text = errorState.harga ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok,
            onValueChange = {
                onValueChange(barangEvent.copy(stok = it))
            },
            label = { Text("Stok") },
            isError = errorState.stok != null,
            placeholder = { Text("Masukkan Stok") }
        )
        Text(
            text = errorState.stok ?: "",
            color = Color.Red
        )

        var selectedSuplier by remember { mutableStateOf(barangEvent.namaSuplier) }
        var expanded by remember { mutableStateOf(false) }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = selectedSuplier,
            onValueChange = {},
            label = { Text("Nama Suplier") },
            placeholder = { Text("Pilih Nama Suplier") },
            readOnly = true,
            isError = errorState.namaSuplier != null,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                }
            }
        )

        // Menampilkan menu dropdown dengan list suplier
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            NamaSuplier.suplier.forEach { suplier ->
                DropdownMenuItem(
                    onClick = {
                        // Mengupdate namaSuplier saat dipilih
                        selectedSuplier = suplier
                        onValueChange(barangEvent.copy(namaSuplier = suplier))
                        expanded = false
                    },
                    text = { Text(suplier) }
                )
            }
        }

    }
}