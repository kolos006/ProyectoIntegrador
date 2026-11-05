package com.example.prestakey.data.model

/**
 * Modelo de datos para representar una llave de aula.
 */
data class KeyItem(
    val id: String,
    val classroomCode: String,
    val classroomName: String,
    val nfcTagId: String,
    val status: KeyStatus = KeyStatus.AVAILABLE,
    val location: String = "Llavero Central",
    val description: String = ""
)
