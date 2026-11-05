package com.example.prestakey.data.repository

import com.example.prestakey.data.model.KeyItem
import com.example.prestakey.data.model.KeyStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repositorio central de la aplicación PrestaKey.
 */
class PrestaKeyRepository {

    // Lista inicial de llaves del centro
    private val _keys = MutableStateFlow(
        listOf(
            KeyItem("KEY-A01", "A01", "Laboratorio Mac", "04:A1:B2:C3", KeyStatus.AVAILABLE, "Edificio Principal - Planta 1"),
            KeyItem("KEY-B12", "B12", "Aula B12", "04:E5:F6:G7", KeyStatus.BORROWED, "Edificio Norte - Planta 2"),
            KeyItem("KEY-P02", "P02", "Plató de Animación", "04:11:22:33", KeyStatus.AVAILABLE, "Edificio Medialab"),
            KeyItem("KEY-BIB", "BIB", "Biblioteca Central", "04:44:55:66", KeyStatus.AVAILABLE, "Edificio Biblioteca"),
            KeyItem("KEY-PRO", "PRO", "Sala de Profesores", "04:77:88:99", KeyStatus.AVAILABLE, "Edificio Principal"),
            KeyItem("KEY-C01", "C01", "Taller de Robótica", "04:AA:BB:CC", KeyStatus.IN_REVIEW, "Edificio Sur")
        )
    )
    val keys: StateFlow<List<KeyItem>> = _keys.asStateFlow()

    /**
     * Busca una llave por su UID de NFC.
     */
    fun findKeyByNfcTag(nfcTagId: String): KeyItem? {
        // Compara ignorando mayúsculas/minúsculas y quitando posibles espacios o dos puntos
        val cleanedInput = nfcTagId.replace(":", "").trim()
        return _keys.value.find { key ->
            key.nfcTagId.replace(":", "").trim().equals(cleanedInput, ignoreCase = true)
        }
    }
}
