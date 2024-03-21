package com.zntkr.mybookapplication.core.data.repository

import com.zntkr.mybookapplication.core.data.model.Book
import com.zntkr.mybookapplication.core.data.model.Books
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getBooks(date: String, list: String) : Flow<Books>

    suspend fun addBooksToDatabase(data: Books)

    suspend fun clearDatabase()

    suspend fun getBooksFromDatabase() : Flow<Books>

    suspend fun getBookDetail(id: Int) : Flow<Book>

    suspend fun addToFavorite(book: Book)
}