package com.example.prestakey.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.prestakey.ui.theme.PrimaryBlue
import com.example.prestakey.ui.theme.PrimaryBlueLight

/** Cuadrado azul claro con el código del aula (B12, P02...). */
@Composable
fun CodeBox(codigo: String) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(PrimaryBlueLight, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = codigo,
            style = MaterialTheme.typography.labelLarge,
            color = PrimaryBlue
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CodeBoxPreview() {
    PrestaKeyTheme {
        CodeBox(codigo = stringResource(R.string.code_b12))
    }
}
