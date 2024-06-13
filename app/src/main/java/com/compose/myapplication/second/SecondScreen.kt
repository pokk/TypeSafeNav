package com.compose.myapplication.second

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    title: String,
    onBookClick: () -> Unit,
) {
    ListOfBooksScreen(
        modifier = modifier,
        title = title,
        onBookClick = onBookClick,
    )
}

@Composable
fun ListOfBooksScreen(
    modifier: Modifier = Modifier,
    title: String,
    onBookClick: () -> Unit = {},
) {
//    LazyColumn(
//        modifier = modifier.fillMaxSize(),
//    ) {
//        items(20) {
    Text(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onBookClick() },
        text = title,
    )
}
//    }
// }
