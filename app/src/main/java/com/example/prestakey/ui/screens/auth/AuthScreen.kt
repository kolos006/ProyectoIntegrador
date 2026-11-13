package com.example.prestakey.ui.screens.auth

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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import com.example.prestakey.ui.components.InfoNote
import com.example.prestakey.ui.theme.PrestaKeyTheme
import com.example.prestakey.ui.theme.PrimaryBlue
import com.example.prestakey.ui.theme.PrimaryBlueDark
import com.example.prestakey.ui.theme.SelectedBlue
import com.example.prestakey.ui.theme.SurfaceCard
import com.example.prestakey.ui.theme.TextMuted
import com.example.prestakey.ui.theme.TextSecondary

/**
 * Pantalla 1: Autenticación / Login.
 * Mockup estático: los campos muestran datos de ejemplo y el botón no hace nada.
 */
@Composable
fun AuthScreen() {
    Scaffold { valoresDeRelleno ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(valoresDeRelleno)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { CabeceraUtad() }
            item { BannerBienvenida() }

            // Título
            item {
                Column {
                    Text(
                        text = stringResource(R.string.login_title),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = stringResource(R.string.login_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextMuted
                    )
                }
            }

            // Campo correo
            item {
                Column {
                    Text(
                        text = stringResource(R.string.login_email_label),
                        style = MaterialTheme.typography.labelLarge,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = stringResource(R.string.login_email_value),
                        onValueChange = {},
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SurfaceCard, RoundedCornerShape(12.dp))
                    )
                }
            }

            // Campo contraseña
            item {
                Column {
                    Text(
                        text = stringResource(R.string.login_password_label),
                        style = MaterialTheme.typography.labelLarge,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = stringResource(R.string.login_password_value),
                        onValueChange = {},
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SurfaceCard, RoundedCornerShape(12.dp))
                    )
                }
            }

            // Recordarme + ¿Olvidaste tu contraseña?
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = true, onCheckedChange = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.login_remember_me),
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = stringResource(R.string.login_forgot_password),
                        style = MaterialTheme.typography.labelLarge,
                        color = PrimaryBlue
                    )
                }
            }

            // Botón Acceder
            item {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.login_button_access),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item { InfoNote(texto = stringResource(R.string.login_note)) }

            // Pie de página
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.login_footer_help),
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                    Text(
                        text = stringResource(R.string.login_footer_disclaimer),
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }
        }
    }
}

/** Logo cuadrado azul + "U-Tad" y el nombre del centro. */
@Composable
fun CabeceraUtad() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(PrimaryBlue, RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_key_outline),
                contentDescription = stringResource(R.string.cd_logo),
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(
                text = stringResource(R.string.brand_title),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.brand_subtitle),
                style = MaterialTheme.typography.bodySmall,
                color = TextMuted
            )
        }
    }
}

/** Tarjeta azul clara "Tu centro, en buenas manos." con una llave a la derecha. */
@Composable
fun BannerBienvenida() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SelectedBlue, RoundedCornerShape(22.dp))
            .padding(22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.login_banner_tag),
                style = MaterialTheme.typography.labelSmall,
                color = PrimaryBlue
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.login_banner_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlueDark
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.login_banner_categories),
                style = MaterialTheme.typography.bodySmall,
                color = PrimaryBlue
            )
        }
        // Círculo decorativo con la llave
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(SurfaceCard, RoundedCornerShape(40.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_key_outline),
                contentDescription = stringResource(R.string.cd_key),
                tint = PrimaryBlue,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AuthScreenPreview() {
    PrestaKeyTheme {
        AuthScreen()
    }
}
