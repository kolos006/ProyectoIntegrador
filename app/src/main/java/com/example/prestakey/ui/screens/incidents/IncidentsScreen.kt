package com.example.prestakey.ui.screens.incidents

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.prestakey.ui.theme.PrimaryBlueDark
import com.example.prestakey.ui.theme.PrimaryBlueLight
import com.example.prestakey.ui.theme.SelectedBlue
import com.example.prestakey.ui.theme.StatusRevisionText
import com.example.prestakey.ui.theme.SurfaceCard
import com.example.prestakey.ui.theme.TextMuted
import com.example.prestakey.ui.theme.TextSecondary

/**
 * Pantalla 5: Reporte de incidencias.
 * Formulario con datos de ejemplo; el botón "Enviar reporte" no envía nada.
 */
@Composable
fun IncidentsScreen() {
    Scaffold(
        topBar = { UtadTopBar(titulo = stringResource(R.string.incidents_topbar_title)) },
        bottomBar = { BottomNavBar(seleccionada = 4) }
    ) { valoresDeRelleno ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(valoresDeRelleno)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        )   {
            // Título + "Borrador"
            item {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.incidents_title),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.weight(1f)
                        )
                        StatusBadge(
                            texto = stringResource(R.string.incidents_draft),
                            fondo = SelectedBlue,
                            colorTexto = PrimaryBlueDark
                        )
                    }
                    Text(
                        text = stringResource(R.string.incidents_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextMuted
                    )
                }
            }

            // Llave afectada (selector de ejemplo)
            item {
                Column {
                    Etiqueta(texto = stringResource(R.string.incidents_key_label))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SurfaceCard, RoundedCornerShape(12.dp))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_key_outline),
                            contentDescription = stringResource(R.string.cd_key),
                            tint = PrimaryBlue,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = stringResource(R.string.incidents_key_value),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = TextSecondary
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.incidents_key_status),
                        style = MaterialTheme.typography.bodySmall,
                        color = StatusRevisionText
                    )
                }
            }

            // Tipo de incidencia
            item {
                Column {
                    Etiqueta(texto = stringResource(R.string.incidents_type_label))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OptionChip(texto = stringResource(R.string.incidents_type_damage), seleccionado = true)
                        OptionChip(texto = stringResource(R.string.incidents_type_loss), seleccionado = false)
                        OptionChip(texto = stringResource(R.string.incidents_type_other), seleccionado = false)
                    }
                }
            }

            // Asunto
            item {
                Column {
                    Etiqueta(texto = stringResource(R.string.incidents_subject_label))
                    OutlinedTextField(
                        value = stringResource(R.string.incidents_subject_value),
                        onValueChange = {},
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SurfaceCard, RoundedCornerShape(12.dp))
                    )
                }
            }

            // Descripción
            item {
                Column {
                    Etiqueta(texto = stringResource(R.string.incidents_description_label))
                    OutlinedTextField(
                        value = stringResource(R.string.incidents_description_value),
                        onValueChange = {},
                        minLines = 3,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SurfaceCard, RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.incidents_description_help),
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }

            // Quién reporta
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PrimaryBlueLight, RoundedCornerShape(12.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.incidents_reporter),
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                }
            }

            // Botón Enviar reporte
            item {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.incidents_button_send),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item { InfoNote(texto = stringResource(R.string.incidents_note)) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

/** Texto pequeño encima de cada campo del formulario. */
@Composable
fun Etiqueta(texto: String) {
    Text(
        text = texto,
        style = MaterialTheme.typography.labelLarge,
        color = TextSecondary,
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IncidentsScreenPreview() {
    PrestaKeyTheme {
        IncidentsScreen()
    }
}
