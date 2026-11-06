package com.example.prestakey.ui.screens.loans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.prestakey.ui.components.CodeBox
import com.example.prestakey.ui.components.InfoNote
import com.example.prestakey.ui.components.StatusBadge
import com.example.prestakey.ui.components.UtadTopBar
import com.example.prestakey.ui.theme.BorderLight
import com.example.prestakey.ui.theme.PrestaKeyTheme
import com.example.prestakey.ui.theme.PrimaryBlue
import com.example.prestakey.ui.theme.PrimaryBlueDark
import com.example.prestakey.ui.theme.PrimaryBlueLight
import com.example.prestakey.ui.theme.SelectedBlue
import com.example.prestakey.ui.theme.StatusBorrowedBg
import com.example.prestakey.ui.theme.StatusBorrowedText
import com.example.prestakey.ui.theme.SurfaceCard
import com.example.prestakey.ui.theme.TextMuted

/**
 * Pantalla 3: Préstamos activos y devolución.
 * Dos tarjetas de préstamo con su botón "Devolver" (mockup estático).
 */
@Composable
fun LoansScreen() {
    Scaffold(
        topBar = { UtadTopBar(titulo = stringResource(R.string.loans_topbar_title)) },
        bottomBar = { BottomNavBar(seleccionada = 2) }
    ) { valoresDeRelleno ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(valoresDeRelleno)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Título + "2 activos"
            item {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.loans_title),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.weight(1f)
                        )
                        StatusBadge(
                            texto = stringResource(R.string.loans_active_count),
                            fondo = StatusBorrowedBg,
                            colorTexto = StatusBorrowedText
                        )
                    }
                    Text(
                        text = stringResource(R.string.loans_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextMuted
                    )
                }
            }

            // Préstamo propio (botón azul fuerte)
            item {
                TarjetaPrestamo(
                    codigo = stringResource(R.string.code_b12),
                    aula = stringResource(R.string.key_b12_name),
                    planta = stringResource(R.string.loans_on_loan_floor2),
                    iniciales = stringResource(R.string.user_initials),
                    docente = stringResource(R.string.loans_teacher_ana),
                    asignatura = stringResource(R.string.loans_subject_ana),
                    recogida = stringResource(R.string.loans_pickup_b12),
                    devolucion = stringResource(R.string.loans_return_b12),
                    esMio = true
                )
            }

            // Préstamo de otro docente (botón azul claro)
            item {
                TarjetaPrestamo(
                    codigo = stringResource(R.string.code_p02),
                    aula = stringResource(R.string.key_p02_name),
                    planta = stringResource(R.string.loans_on_loan_floor1),
                    iniciales = stringResource(R.string.loans_initials_mv),
                    docente = stringResource(R.string.loans_teacher_marcos),
                    asignatura = stringResource(R.string.loans_subject_marcos),
                    recogida = stringResource(R.string.loans_pickup_p02),
                    devolucion = stringResource(R.string.loans_return_p02),
                    esMio = false
                )
            }

            // Aviso de secretaría
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PrimaryBlueLight, RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = stringResource(R.string.cd_info),
                        tint = PrimaryBlue
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = stringResource(R.string.loans_notice_title),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.loans_notice_text),
                            style = MaterialTheme.typography.bodySmall,
                            color = TextMuted
                        )
                    }
                }
            }

            item { InfoNote(texto = stringResource(R.string.loans_note)) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

/** Tarjeta de un préstamo: aula, docente, horas y botón Devolver. */
@Composable
fun TarjetaPrestamo(
    codigo: String,
    aula: String,
    planta: String,
    iniciales: String,
    docente: String,
    asignatura: String,
    recogida: String,
    devolucion: String,
    esMio: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceCard, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        // Aula
        Row(verticalAlignment = Alignment.CenterVertically) {
            CodeBox(codigo = codigo)
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = aula,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = planta,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_key_outline),
                contentDescription = stringResource(R.string.cd_key),
                tint = PrimaryBlue,
                modifier = Modifier.size(24.dp)
            )
        }

        // Línea separadora
        Box(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(BorderLight)
        )

        // Docente
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(PrimaryBlueLight, RoundedCornerShape(18.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = iniciales,
                    style = MaterialTheme.typography.labelSmall,
                    color = PrimaryBlueDark
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = docente,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = asignatura,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted
                )
            }
            if (esMio) {
                StatusBadge(
                    texto = stringResource(R.string.loans_mine),
                    fondo = StatusBorrowedBg,
                    colorTexto = StatusBorrowedText
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Horas
        Row {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.loans_pickup_label),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted
                )
                Text(text = recogida, style = MaterialTheme.typography.titleMedium)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.loans_return_label),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted
                )
                Text(text = devolucion, style = MaterialTheme.typography.titleMedium)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón Devolver: azul fuerte si la llave es mía, azul claro si es de otro
        val colores = if (esMio) {
            ButtonDefaults.buttonColors()
        } else {
            ButtonDefaults.buttonColors(containerColor = SelectedBlue, contentColor = PrimaryBlueDark)
        }
        Button(
            onClick = {},
            colors = colores,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.loans_button_return))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoansScreenPreview() {
    PrestaKeyTheme {
        LoansScreen()
    }
}
