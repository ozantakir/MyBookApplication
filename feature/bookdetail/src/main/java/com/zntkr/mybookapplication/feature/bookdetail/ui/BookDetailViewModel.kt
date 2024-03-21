package com.zntkr.mybookapplication.feature.bookdetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zntkr.mybookapplication.core.data.model.Book
import com.zntkr.mybookapplication.core.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BookDetailUiState>(BookDetailUiState.Loading)
    val uiState: StateFlow<BookDetailUiState> = _uiState

    fun getBookDetail(id: Int) {
        viewModelScope.launch {
            bookRepository.getBookDetail(id)
                .onStart {
                    _uiState.value = BookDetailUiState.Loading
                }
                .catch {
                    _uiState.value = BookDetailUiState.Error
                }
                .collect {
                    _uiState.value = BookDetailUiState.Success(it)
                }
        }
    }
    fun addToFavorite(book: Book) {
        viewModelScope.launch {
            bookRepository.addToFavorite(book)
            getBookDetail(book.uid)
        }
    }
}

sealed interface BookDetailUiState {
    data class Success(val response: Book) : BookDetailUiState
    data object Error : BookDetailUiState
    data object Loading : BookDetailUiState
}