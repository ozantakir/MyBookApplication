package com.zntkr.mybookapplication.feature.booklist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import com.zntkr.mybookapplication.core.data.model.Book
import com.zntkr.mybookapplication.core.design.component.DynamicAsyncImage


@Composable
fun BookListItem(
    modifier: Modifier,
    onFavoriteClick: () -> Unit,
    bookItem: Book,
    onItemClick: () -> Unit,
    isFavorite: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable {
                onItemClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .weight(1f)
        ) {
            DynamicAsyncImage(
                imageUrl = bookItem.bookImage.orEmpty(),
                contentDescription = null,
                modifier = modifier
                    .size(50.dp)
            )
            Column(
                modifier = modifier
                    .padding(start = 4.dp)
            ) {
                Text(
                    text = bookItem.title.orEmpty(),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = bookItem.author.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        if (isFavorite) {
            IconButton(
                onClick = { onFavoriteClick() },
            ) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "favorite")
            }
        }
    }
}