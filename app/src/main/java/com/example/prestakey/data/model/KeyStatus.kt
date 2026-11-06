package com.example.prestakey.data.model

/**
 * Enum que representa los estados posibles de una llave:
 * - AVAILABLE: Disponible para ser prestada.
 * - BORROWED: Actualmente prestada a un docente.
 * - IN_REVIEW: En revisión por incidencia o desperfecto.
 */
enum class KeyStatus {
    AVAILABLE,
    BORROWED,
    IN_REVIEW
}
