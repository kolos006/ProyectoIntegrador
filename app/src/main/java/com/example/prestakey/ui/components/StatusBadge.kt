package com.example.prestakey.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prestakey.R
import com.example.prestakey.ui.theme.PrestaKeyTheme
import com.example.prestakey.ui.theme.StatusAvailableBg
import com.example.prestakey.ui.theme.StatusAvailableText

/**
 * Etiqueta pequeña de color para el estado de una llave
 * (Disponible = verde, Prestada = azul, En revisión = naranja).
 */
@Composable
fun StatusBadge(texto: String, fondo: Color, colorTexto: Color) {
    Text(
        text = texto,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.SemiBold,
        color = colorTexto,
        modifier = Modifier
            .background(fondo, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun StatusBadgePreview() {
    PrestaKeyTheme {
        StatusBadge(
            texto = stringResource(R.string.status_available),
            fondo = StatusAvailableBg,
            colorTexto = StatusAvailableText
        )
    }
}
