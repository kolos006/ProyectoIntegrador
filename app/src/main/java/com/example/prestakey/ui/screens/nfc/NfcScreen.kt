package com.example.prestakey.ui.screens.nfc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.prestakey.R
import com.example.prestakey.ui.components.BottomNavBar
import com.example.prestakey.ui.components.UtadTopBar
import com.example.prestakey.ui.theme.PrestaKeyTheme
import com.example.prestakey.ui.theme.PrimaryBlue
import com.example.prestakey.ui.theme.SelectedBlue
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

/**
 * Pantalla 4: Lectura NFC.
 * 1. Se acerca la llave (tag NFC) al móvil.
 * 2. Se abre el escáner y se lee el código de barras de la persona.
 * 3. Aparece un pop up: "Esta llave ha sido prestada a X".
 */
@Composable
fun NfcScreen(
    viewModel: NfcViewModel = run {
        val context = LocalContext.current
        val isPreview = LocalInspectionMode.current
        if (!isPreview && context is ComponentActivity) {
            ViewModelProvider(context)[NfcViewModel::class.java]
        } else {
            remember { NfcViewModel() }
        }
    }
) {
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current
    val uiState by viewModel.uiState.collectAsState()

    val scannerOptions = remember {
        GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .enableAutoZoom()
            .build()
    }

    // Cuando el ViewModel ordena abrir el escáner (tras detectar el tag NFC)
    LaunchedEffect(uiState.shouldLaunchScanner) {
        if (!isPreview && uiState.shouldLaunchScanner) {
            viewModel.onScannerLaunched()
            GmsBarcodeScanning.getClient(context, scannerOptions)
                .startScan()
                .addOnSuccessListener { barcode -> viewModel.onCodeScanned(barcode.rawValue) }
                .addOnCanceledListener { viewModel.onScanFailed() }
                .addOnFailureListener { viewModel.onScanFailed() }
        }
    }

    // Escuchar el Broadcast de NFC que emite MainActivity
    if (!isPreview) {
        DisposableEffect(context) {
            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    val tagId = intent?.getStringExtra("nfc_tag_id")
                    if (!tagId.isNullOrEmpty()) viewModel.onTagDetected(tagId)
                }
            }
            ContextCompat.registerReceiver(
                context,
                receiver,
                IntentFilter("com.example.prestakey.NFC_SCANNED"),
                ContextCompat.RECEIVER_NOT_EXPORTED
            )
            onDispose { context.unregisterReceiver(receiver) }
        }
    }

    Scaffold(
        topBar = { UtadTopBar(titulo = stringResource(R.string.nfc_topbar_title)) },
        bottomBar = { BottomNavBar(seleccionada = 3) }
    ) { valoresDeRelleno ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(valoresDeRelleno),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(SelectedBlue, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = stringResource(R.string.nfc_title),
                    tint = PrimaryBlue,
                    modifier = Modifier.size(90.dp)
                )
            }
        }
    }

    // Pop up de confirmación del préstamo
    uiState.borrowerCode?.let { persona ->
        AlertDialog(
            onDismissRequest = { viewModel.resetScan() },
            confirmButton = {
                TextButton(onClick = { viewModel.resetScan() }) { Text("Aceptar") }
            },
            text = { Text("Esta llave ha sido prestada a $persona") }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NfcScreenPreview() {
    PrestaKeyTheme {
        NfcScreen()
    }
}