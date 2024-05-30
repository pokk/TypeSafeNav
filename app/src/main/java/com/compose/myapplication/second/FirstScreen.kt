package com.compose.myapplication.second

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit = {},
) {
    Column {
        Text(
            modifier = modifier,
            text = "Hello World!",
        )

        Button(onClick = onClicked) {
            Text(text = "Click Me")
        }
    }
}
