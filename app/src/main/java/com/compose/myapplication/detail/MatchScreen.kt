package com.compose.myapplication.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun MatchScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            style =
                TextStyle(
                    color = Color.Red,
                ),
            text = "this is so Matchhhhhhhhhhhhhhhhhhhhhh Detail Screen",
        )
        Button(
            onClick = onClick,
        ) {
            Text(text = "Click MMMEEE")
        }
    }
}
