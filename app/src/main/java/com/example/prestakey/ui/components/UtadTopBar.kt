package com.example.prestakey.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prestakey.R
import com.example.prestakey.ui.theme.PrestaKeyTheme
import com.example.prestakey.ui.theme.PrimaryBlue
import com.example.prestakey.ui.theme.PrimaryBlueDark
import com.example.prestakey.ui.theme.SelectedBlue
import com.example.prestakey.ui.theme.TextMuted

/**
 * Barra superior común a las pantallas 2-5: logo, título de la pantalla,
 * subtítulo "U-Tad" y las iniciales del usuario a la derecha.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UtadTopBar(titulo: String, onLogout: () -> Unit = {}) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Logo: cuadrado azul con una llave
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(PrimaryBlue, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_key_outline),
                        contentDescription = stringResource(R.string.cd_logo),
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = titulo,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(R.string.brand_title),
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }
        },
        actions = {
            var menuAbierto by remember { mutableStateOf(false) }

            // Avatar con las iniciales del usuario y menú desplegable
            Box(modifier = Modifier.padding(end = 12.dp)) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(SelectedBlue, RoundedCornerShape(20.dp))
                        .clickable { menuAbierto = true },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.user_initials),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlueDark
                    )
                }

                DropdownMenu(
                    expanded = menuAbierto,
                    onDismissRequest = { menuAbierto = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.action_logout)) },
                        onClick = {
                            menuAbierto = false
                            onLogout()
                        }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun UtadTopBarPreview() {
    PrestaKeyTheme {
        UtadTopBar(titulo = stringResource(R.string.keys_topbar_title))
    }
}
