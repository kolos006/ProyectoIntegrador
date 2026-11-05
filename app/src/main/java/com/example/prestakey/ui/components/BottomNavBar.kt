package com.example.prestakey.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prestakey.R
import com.example.prestakey.ui.theme.PrestaKeyTheme
import com.example.prestakey.ui.theme.PrimaryBlueDark
import com.example.prestakey.ui.theme.PrimaryBlueLight
import com.example.prestakey.ui.theme.SelectedBlue
import com.example.prestakey.ui.theme.TextSecondary

/**
 * Barra inferior con las 4 secciones (Llaves, Préstamos, NFC, Incidencias).
 * Es un mockup estático: solo marca cuál está seleccionada (1 a 4), no navega.
 */
@Composable
fun BottomNavBar(seleccionada: Int) {
    BottomAppBar(containerColor = PrimaryBlueLight) {
        BottomNavItem(
            icono = Icons.Default.Lock,
            texto = stringResource(R.string.nav_keys),
            seleccionado = seleccionada == 1,
            modifier = Modifier.weight(1f)
        )
        BottomNavItem(
            icono = Icons.Default.DateRange,
            texto = stringResource(R.string.nav_loans),
            seleccionado = seleccionada == 2,
            modifier = Modifier.weight(1f)
        )
        BottomNavItem(
            icono = Icons.Default.Phone,
            texto = stringResource(R.string.nav_nfc),
            seleccionado = seleccionada == 3,
            modifier = Modifier.weight(1f)
        )
        BottomNavItem(
            icono = Icons.Default.Warning,
            texto = stringResource(R.string.nav_incidents),
            seleccionado = seleccionada == 4,
            modifier = Modifier.weight(1f)
        )
    }
}

/** Un botón de la barra inferior: icono (con fondo si está seleccionado) y texto. */
@Composable
fun BottomNavItem(icono: ImageVector, texto: String, seleccionado: Boolean, modifier: Modifier) {
    val fondo = if (seleccionado) SelectedBlue else Color.Transparent
    val color = if (seleccionado) PrimaryBlueDark else TextSecondary

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(fondo, RoundedCornerShape(16.dp))
                .padding(horizontal = 18.dp, vertical = 4.dp)
        ) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(22.dp)
            )
        }
        Text(
            text = texto,
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    PrestaKeyTheme {
        BottomNavBar(seleccionada = 1)
    }
}
