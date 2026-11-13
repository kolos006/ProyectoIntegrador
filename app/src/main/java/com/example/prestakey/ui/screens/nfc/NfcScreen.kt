package com.example.prestakey.ui.screens.nfc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.prestakey.R
import com.example.prestakey.ui.components.BottomNavBar
import com.example.prestakey.ui.components.UtadTopBar
import com.example.prestakey.ui.theme.PrestaKeyTheme
import com.example.prestakey.ui.theme.PrimaryBlue
import com.example.prestakey.ui.theme.StatusAvailableBg
import com.example.prestakey.ui.theme.StatusAvailableText
import com.example.prestakey.ui.theme.SurfaceCard
import com.example.prestakey.ui.theme.TextPrimary
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.delay

/**
 * Pantalla 4: Lectura NFC.
 * 1. Se acerca la llave (tag NFC) al móvil.
 * 2. Se abre el escáner y se lee el código de barras de la persona.
 * 3. Aparece un pop up arriba del todo que desaparece solo a los 15-20 segundos:
 *    "Esta llave ha sido prestada a X".
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

    // Ocultar automáticamente el pop up superior tras 17 segundos (entre 15 y 20 s)
    var ultimoCodigoLeido by remember { mutableStateOf("") }
    LaunchedEffect(uiState.borrowerCode) {
        val codigo = uiState.borrowerCode
        if (codigo != null) {
            ultimoCodigoLeido = codigo
            delay(5_000L)
            viewModel.resetScan()
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
                .padding(valoresDeRelleno)
        ) {
            // Icono central de lectura NFC
            Icon(
                painter = painterResource(id = R.drawable.ic_nfc_phone),
                contentDescription = stringResource(R.string.nfc_title),
                tint = PrimaryBlue,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center)
            )

            // Pop up emergente arriba del todo (sin botón de cerrar, desaparece solo)
            AnimatedVisibility(
                visible = uiState.borrowerCode != null,
                enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(StatusAvailableBg, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = StatusAvailableText,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Esta llave ha sido prestada a ${uiState.borrowerCode ?: ultimoCodigoLeido}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NfcScreenPreview() {
    PrestaKeyTheme {
        NfcScreen()
    }
}