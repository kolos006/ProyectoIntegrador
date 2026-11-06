package com.example.prestakey.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
fun UtadTopBar(titulo: String) {
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
            // Avatar con las iniciales del usuario
            Box(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(40.dp)
                    .background(SelectedBlue, RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.user_initials),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlueDark
                )
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
