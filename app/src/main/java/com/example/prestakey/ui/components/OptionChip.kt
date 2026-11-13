package com.example.prestakey.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prestakey.R
import com.example.prestakey.ui.theme.PrestaKeyTheme
import com.example.prestakey.ui.theme.PrimaryBlueDark
import com.example.prestakey.ui.theme.SelectedBlue
import com.example.prestakey.ui.theme.SurfaceCard
import com.example.prestakey.ui.theme.TextSecondary

/**
 * Opción tipo "chip" (filtros de llaves y tipo de incidencia).
 * Si está seleccionada lleva fondo azul claro y un check delante.
 */
@Composable
fun OptionChip(texto: String, seleccionado: Boolean) {
    val fondo = if (seleccionado) SelectedBlue else SurfaceCard
    val color = if (seleccionado) PrimaryBlueDark else TextSecondary

    Row(
        modifier = Modifier
            .background(fondo, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (seleccionado) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Text(
            text = texto,
            style = MaterialTheme.typography.labelLarge,
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OptionChipPreview() {
    PrestaKeyTheme {
        OptionChip(texto = stringResource(R.string.keys_filter_all), seleccionado = true)
    }
}
