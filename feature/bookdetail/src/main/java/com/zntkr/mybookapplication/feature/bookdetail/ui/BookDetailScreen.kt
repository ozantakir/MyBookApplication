package com.zntkr.mybookapplication.feature.bookdetail.ui

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zntkr.mybookapplication.core.design.component.DynamicAsyncImage
import com.zntkr.mybookapplication.core.design.component.LoadingView
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BookDetailScreen(
    navController: NavController,
    id: String?,
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    id?.toIntOrNull()?.let {
        viewModel.getBookDetail(it)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is BookDetailUiState.Success -> {
            val response = (uiState as BookDetailUiState.Success).response
            BackHandler(
                enabled = true,
            ) {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("clickedBook", response)
                navController.popBackStack()
            }
            Column {
                DynamicAsyncImage(
                    imageUrl = response.bookImage.orEmpty(),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Row {
                    TextRow(title = "Title", info = response.title)
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            viewModel.addToFavorite(response.copy(isFavorite = response.isFavorite.not()))
                        },
                    ) {
                        Icon(
                            imageVector = if (response.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "favorite"
                        )
                    }
                }

                TextRow(title = "Author", info = response.author)
                TextRow(title = "Rank", info = response.rank.toString())
                TextRow(title = "Publisher", info = response.publisher)
                TextRow(title = "Description", info = response.description)
            }
        }

        BookDetailUiState.Loading -> {
            LoadingView(modifier = Modifier)
        }

        BookDetailUiState.Error -> {
            Toast.makeText(context, "Error Occured", Toast.LENGTH_SHORT).show()
            navController.navigate("main") {
                popUpTo(0)
            }
        }
    }
}


@Composable
fun TextRow(
    title: String,
    info: String?
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Text(
            text = title.plus(":"),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.Top)
        )
        Text(
            text = info.orEmpty(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}