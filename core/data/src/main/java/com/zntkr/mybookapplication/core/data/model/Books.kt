package com.zntkr.mybookapplication.core.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.zntkr.mybookapplication.core.database.model.BookEntity
import com.zntkr.mybookapplication.core.network.model.BookItem
import com.zntkr.mybookapplication.core.network.model.BooksResponse

@Keep
data class Books(
    val books: List<Book>
)

@Keep
data class Book(
    val uid: Int = 0,
    val rank: Int? = null,
    val publisher: String? = null,
    val description: String? = null,
    val title: String? = null,
    val author: String? = null,
    val bookImage: String? = null,
    val amazonProductUrl: String? = null,
    val isFavorite: Boolean = false
)

fun BooksResponse.asBook() : Books {
    val result = arrayListOf<Book>()
    books.forEach {
        result.add(it.asBook())
    }
    return Books(result)
}

fun BookItem.asBook() = Book(
    uid = uid,
    publisher = publisher,
    description = description,
    title = title,
    author = author,
    bookImage = bookImage,
    amazonProductUrl = amazonProductUrl,
    rank = rank,
    isFavorite = isFavorite
)

fun Book.asEntity() = BookEntity(
    uid = uid,
    publisher = publisher,
    description = description,
    title = title,
    author = author,
    bookImage = bookImage,
    amazonProductUrl = amazonProductUrl,
    rank = rank,
    isFavorite = isFavorite
)

fun BookEntity.asBook() = Book(
    uid = uid,
    publisher = publisher,
    description = description,
    title = title,
    author = author,
    bookImage = bookImage,
    amazonProductUrl = amazonProductUrl,
    rank = rank,
    isFavorite = isFavorite
)

fun List<BookEntity>.asBooks() : Books {
    val result = arrayListOf<Book>()
    this.forEach {
        result.add(it.asBook())
    }
    return Books(result)
}