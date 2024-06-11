package com.compose.myapplication.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun InGameScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "....................... In Game Screen \uD83E\uDD14",
        )
        Button(
            onClick = onClick,
        ) {
            Text(text = "Click MMMEEE")
        }
    }
}
