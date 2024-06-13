package com.compose.myapplication.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ResultWinnerScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "....................... This is the resulttttt \uD83C\uDFC6\uD83C\uDFC6\uD83C\uDFC6\uD83C\uDFC6",
        )
        Button(
            onClick = onClick,
        ) {
            Text(text = "Go to Second")
        }
    }
}
