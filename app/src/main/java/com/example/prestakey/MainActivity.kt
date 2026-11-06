package com.example.prestakey

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.prestakey.ui.screens.nfc.NfcScreen
import com.example.prestakey.ui.screens.nfc.NfcViewModel
import com.example.prestakey.ui.theme.PrestaKeyTheme

class MainActivity : ComponentActivity() {

    private var nfcAdapter: NfcAdapter? = null

    // El ViewModel vinculado a la Activity sobrevive a la rotación de pantalla
    private val nfcViewModel: NfcViewModel by lazy {
        ViewModelProvider(this)[NfcViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        setContent {
            PrestaKeyTheme {
                NfcScreen(viewModel = nfcViewModel)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Modo lector universal: captura cualquier tecnología NFC existente
        nfcAdapter?.enableReaderMode(
            this,
            { tag: Tag ->
                procesarTagNfc(tag)
            },
            NfcAdapter.FLAG_READER_NFC_A or
            NfcAdapter.FLAG_READER_NFC_B or
            NfcAdapter.FLAG_READER_NFC_F or
            NfcAdapter.FLAG_READER_NFC_V or
            NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS,
            null
        )
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableReaderMode(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val tag: Tag? = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(NfcAdapter.EXTRA_TAG, Tag::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
        }
        tag?.let { procesarTagNfc(it) }
    }

    private fun procesarTagNfc(tag: Tag) {
        val idBytes = tag.id ?: byteArrayOf()
        val tagIdHex = if (idBytes.isNotEmpty()) {
            idBytes.joinToString(":") { "%02X".format(it) }
        } else {
            "TAG_SIN_UID"
        }

        // 1. Notificación directa al ViewModel (garantiza entrega inmediata)
        runOnUiThread {
            nfcViewModel.onTagDetected(tagIdHex)
        }

        // 2. Broadcast de respaldo
        val broadcastIntent = Intent("com.example.prestakey.NFC_SCANNED").apply {
            putExtra("nfc_tag_id", tagIdHex)
            setPackage(packageName)
        }
        sendBroadcast(broadcastIntent)
    }
}
