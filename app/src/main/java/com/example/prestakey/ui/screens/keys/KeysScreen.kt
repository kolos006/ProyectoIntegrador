package com.example.prestakey.ui.screens.keys

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prestakey.R
import com.example.prestakey.ui.components.BottomNavBar
import com.example.prestakey.ui.components.InfoNote
import com.example.prestakey.ui.components.OptionChip
import com.example.prestakey.ui.components.StatusBadge
import com.example.prestakey.ui.components.UtadTopBar
import com.example.prestakey.ui.theme.PrestaKeyTheme
import com.example.prestakey.ui.theme.PrimaryBlue
import com.example.prestakey.ui.theme.PrimaryBlueLight
import com.example.prestakey.ui.theme.StatusAvailableBg
import com.example.prestakey.ui.theme.StatusAvailableText
import com.example.prestakey.ui.theme.StatusBorrowedBg
import com.example.prestakey.ui.theme.StatusBorrowedText
import com.example.prestakey.ui.theme.StatusRevisionBg
import com.example.prestakey.ui.theme.StatusRevisionText
import com.example.prestakey.ui.theme.SurfaceCard
import com.example.prestakey.ui.theme.TextMuted

/**
 * Pantalla 2: Panel y consulta de llaves.
 * Contadores, buscador, filtros por estado y rejilla de tarjetas de llaves (mockup estático).
 */
@Composable
fun KeysScreen() {
    Scaffold(
        topBar = { UtadTopBar(titulo = stringResource(R.string.keys_topbar_title)) },
        bottomBar = { BottomNavBar(seleccionada = 1) }
    ) { valoresDeRelleno ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(valoresDeRelleno)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Saludo
            item {
                Column {
                    Text(
                        text = stringResource(R.string.keys_greeting),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = stringResource(R.string.keys_greeting_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextMuted
                    )
                }
            }

            // Contadores
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PrimaryBlueLight, RoundedCornerShape(16.dp))
                        .padding(vertical = 14.dp)
                ) {
                    Contador(
                        numero = stringResource(R.string.keys_count_available),
                        texto = stringResource(R.string.keys_label_available),
                        color = StatusAvailableText,
                        modifier = Modifier.weight(1f)
                    )
                    Contador(
                        numero = stringResource(R.string.keys_count_borrowed),
                        texto = stringResource(R.string.keys_label_borrowed),
                        color = PrimaryBlue,
                        modifier = Modifier.weight(1f)
                    )
                    Contador(
                        numero = stringResource(R.string.keys_count_in_review),
                        texto = stringResource(R.string.status_in_review),
                        color = StatusRevisionText,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Buscador
            item {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(stringResource(R.string.keys_search_hint)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PrimaryBlueLight, RoundedCornerShape(24.dp))
                )
            }

            // Filtros por estado
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OptionChip(texto = stringResource(R.string.keys_filter_all), seleccionado = true)
                    OptionChip(texto = stringResource(R.string.keys_label_available), seleccionado = false)
                    OptionChip(texto = stringResource(R.string.keys_label_borrowed), seleccionado = false)
                }
            }

            // Título de la lista
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.keys_list_title),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = stringResource(R.string.keys_list_count),
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }

            // Rejilla de llaves: 3 filas de 2 tarjetas
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    TarjetaLlave(
                        ubicacion = stringResource(R.string.key_a01_location),
                        nombre = stringResource(R.string.key_a01_name),
                        estado = stringResource(R.string.status_available),
                        fondoEstado = StatusAvailableBg,
                        colorEstado = StatusAvailableText,
                        modifier = Modifier.weight(1f)
                    )
                    TarjetaLlave(
                        ubicacion = stringResource(R.string.key_b12_location),
                        nombre = stringResource(R.string.key_b12_name),
                        estado = stringResource(R.string.key_b12_status),
                        fondoEstado = StatusBorrowedBg,
                        colorEstado = StatusBorrowedText,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    TarjetaLlave(
                        ubicacion = stringResource(R.string.key_p02_location),
                        nombre = stringResource(R.string.key_p02_name),
                        estado = stringResource(R.string.key_p02_status),
                        fondoEstado = StatusBorrowedBg,
                        colorEstado = StatusBorrowedText,
                        modifier = Modifier.weight(1f)
                    )
                    TarjetaLlave(
                        ubicacion = stringResource(R.string.key_bib_location),
                        nombre = stringResource(R.string.key_bib_name),
                        estado = stringResource(R.string.status_available),
                        fondoEstado = StatusAvailableBg,
                        colorEstado = StatusAvailableText,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    TarjetaLlave(
                        ubicacion = stringResource(R.string.key_pro_location),
                        nombre = stringResource(R.string.key_pro_name),
                        estado = stringResource(R.string.status_available),
                        fondoEstado = StatusAvailableBg,
                        colorEstado = StatusAvailableText,
                        modifier = Modifier.weight(1f)
                    )
                    TarjetaLlave(
                        ubicacion = stringResource(R.string.key_c01_location),
                        nombre = stringResource(R.string.key_c01_name),
                        estado = stringResource(R.string.status_in_review),
                        fondoEstado = StatusRevisionBg,
                        colorEstado = StatusRevisionText,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item { InfoNote(texto = stringResource(R.string.keys_note)) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

/** Un contador del resumen: número grande de color y texto debajo. */
@Composable
fun Contador(numero: String, texto: String, color: Color, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = numero,
            style = MaterialTheme.typography.headlineSmall,
            color = color
        )
        Text(
            text = texto,
            style = MaterialTheme.typography.bodySmall,
            color = TextMuted
        )
    }
}

/** Tarjeta blanca de una llave: aula y planta, nombre, icono de llave y estado. */
@Composable
fun TarjetaLlave(
    ubicacion: String,
    nombre: String,
    estado: String,
    fondoEstado: Color,
    colorEstado: Color,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .background(SurfaceCard, RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = ubicacion,
                style = MaterialTheme.typography.labelSmall,
                color = TextMuted,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_key_outline),
                contentDescription = stringResource(R.string.cd_key),
                tint = PrimaryBlue,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = nombre,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        StatusBadge(texto = estado, fondo = fondoEstado, colorTexto = colorEstado)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun KeysScreenPreview() {
    PrestaKeyTheme {
        KeysScreen()
    }
}
