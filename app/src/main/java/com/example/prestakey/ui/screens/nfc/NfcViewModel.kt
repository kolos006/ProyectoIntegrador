package com.example.prestakey.ui.screens.nfc

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Estado de la pantalla NFC.
 * - nfcTagScanned: UID de la llave leída (null si aún no se ha leído).
 * - borrowerCode: código de barras de la persona; cuando no es null se muestra el pop up.
 */
data class NfcUiState(
    val nfcTagScanned: String? = null,
    val borrowerCode: String? = null,
    val shouldLaunchScanner: Boolean = false
)

/**
 * ViewModel del flujo: tag NFC (llave) -> código de barras (persona) -> pop up.
 */
class NfcViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NfcUiState())
    val uiState: StateFlow<NfcUiState> = _uiState.asStateFlow()

    /**
     * Se ejecuta al leer un tag NFC. Abre el escáner de código de barras.
     * MainActivity entrega el tag por dos vías (llamada directa + broadcast),
     * así que se ignora si ya estamos procesando una llave.
     */
    fun onTagDetected(tagId: String) {
        if (_uiState.value.nfcTagScanned != null && _uiState.value.borrowerCode == null) return
        _uiState.update {
            it.copy(nfcTagScanned = tagId, borrowerCode = null, shouldLaunchScanner = true)
        }
    }

    /** Evita reabrir el escáner en bucle. */
    fun onScannerLaunched() {
        _uiState.update { it.copy(shouldLaunchScanner = false) }
    }

    /** Guarda el código de barras de la persona, lo que muestra el pop up. */
    fun onCodeScanned(codeContent: String?) {
        if (codeContent.isNullOrBlank()) {
            resetScan()
        } else {
            _uiState.update { it.copy(borrowerCode = codeContent) }
        }
    }

    /** Escaneo cancelado o fallido: se vuelve a esperar una llave. */
    fun onScanFailed() = resetScan()

    /** Reinicia el estado para leer otra llave. */
    fun resetScan() {
        _uiState.value = NfcUiState()
    }
}