package com.zntkr.mybookapplication.feature.booklist.ui

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.zntkr.mybookapplication.core.data.model.Book
import com.zntkr.mybookapplication.core.design.component.LoadingView
import androidx.compose.foundation.lazy.items

@Composable
fun BookListScreen(
    modifier: Modifier,
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry,
    viewModel: BookListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    when (uiState) {
        BookListUiState.Loading -> {
            LoadingView(modifier = modifier)
        }

        is BookListUiState.Success -> {
            LazyColumn(
                modifier = modifier
                    .padding(top = 8.dp)
            ) {
                items(
                    (uiState as BookListUiState.Success).response.books,
                ) {bookItem ->
                    val clickedBook = navBackStackEntry.savedStateHandle.get<Book>("clickedBook")
                    BookListItem(
                        modifier = modifier,
                        onFavoriteClick = { /*TODO*/ },
                        bookItem = bookItem,
                        onItemClick = {
                            navController.navigate("detail/${bookItem.uid}")
                        },
                        isFavorite = if (clickedBook?.uid == bookItem.uid) clickedBook.isFavorite else bookItem.isFavorite
                    )
                }
            }
        }

        BookListUiState.Error -> {
            Toast.makeText(context, "Error Occured", Toast.LENGTH_SHORT).show()
        }
    }

}