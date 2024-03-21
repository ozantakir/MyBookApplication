package com.zntkr.mybookapplication.feature.booklist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zntkr.mybookapplication.core.data.model.Book
import com.zntkr.mybookapplication.core.data.model.Books
import com.zntkr.mybookapplication.core.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel  @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BookListUiState>(BookListUiState.Loading)
    val uiState: StateFlow<BookListUiState> = _uiState

    init {
        getBooksFromApiAndSaveToDatabase()
    }

    private fun getBooksFromApiAndSaveToDatabase(date: String = "current", list: String ="hardcover-fiction") {
        viewModelScope.launch {
            bookRepository.getBooks(date, list)
                .onStart {
                    _uiState.value = BookListUiState.Loading
                }
                .catch {
                    getBooksToShow(null)
                }
                .collect {
                    getBooksToShow(it)
                }
        }
    }

    private fun getBooksToShow(requestData: Books?) {
        viewModelScope.launch {
            bookRepository.getBooksFromDatabase()
                .onStart {
                    _uiState.value = BookListUiState.Loading
                }
                .catch {
                    _uiState.value = BookListUiState.Error
                }
                .collect { database ->
                    val resultBooks = arrayListOf<Book>()

                    requestData?.books?.forEach { networkBook ->
                        val conflict = database.books.find {
                            networkBook.title == it.title
                        }?.copy(
                            isFavorite = database.books.find {
                                networkBook.title == it.title
                            }?.isFavorite ?: false
                        )
                        if (conflict != null) {
                            resultBooks.add(conflict)
                        } else {
                            resultBooks.add(networkBook)
                        }
                    }
                    val books = if (requestData == null) {
                        database
                    } else {
                        Books(resultBooks)
                    }
                    bookRepository.clearDatabase()
                    bookRepository.addBooksToDatabase(books)
                    _uiState.value = BookListUiState.Success(books)
                }
        }
    }

}

sealed interface BookListUiState {
    data class Success(val response: Books) : BookListUiState
    data object Error : BookListUiState
    data object Loading : BookListUiState
}