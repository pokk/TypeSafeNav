package com.compose.myapplication.first

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    title: String,
) {
    ListOfBooksScreen(
        modifier = modifier,
        title = title
    )
}

@Composable
fun ListOfBooksScreen(
    modifier: Modifier = Modifier,
    title: String,
    onBookClick: () -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(20) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = title,
            )
        }
    }
}
