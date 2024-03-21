package com.zntkr.mybookapplication.core.data.repository

import com.zntkr.mybookapplication.core.data.model.Book
import com.zntkr.mybookapplication.core.data.model.Books
import com.zntkr.mybookapplication.core.data.model.asBook
import com.zntkr.mybookapplication.core.data.model.asBooks
import com.zntkr.mybookapplication.core.data.model.asEntity
import com.zntkr.mybookapplication.core.database.dao.BooksDao
import com.zntkr.mybookapplication.core.database.model.BookEntity
import com.zntkr.mybookapplication.core.network.BookRetrofitDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val network: BookRetrofitDataSource,
    private val dao: BooksDao
): BookRepository {
    override suspend fun getBooks(date: String, list: String): Flow<Books> = flow {
        emit(network.getBooks(date, list).asBook())
    }

    override suspend fun addBooksToDatabase(data: Books) {
        val bookEntityList = arrayListOf<BookEntity>()
        data.books.forEach {
            bookEntityList.add(it.asEntity())
        }
        dao.addBook(bookEntityList)
    }

    override suspend fun clearDatabase() {
        dao.clearTable()
    }

    override suspend fun getBooksFromDatabase(): Flow<Books> = flow {
        emit(dao.getAllBooks().asBooks())
    }

    override suspend fun getBookDetail(id: Int): Flow<Book> = flow {
        emit(dao.getItem(id).asBook())
    }

    override suspend fun addToFavorite(book: Book) {
        dao.addToFavorite(book.asEntity())
    }
}