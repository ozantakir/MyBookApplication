package com.zntkr.mybookapplication.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zntkr.mybookapplication.core.database.model.BookEntity

@Dao
interface BooksDao {

    @Insert
    suspend fun addBook(books: List<BookEntity>)

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<BookEntity>

    @Query("SELECT * FROM books WHERE uid = :id")
    suspend fun getItem(id: Int): BookEntity

    @Query("DELETE FROM books")
    suspend fun clearTable()

    @Update(entity = BookEntity::class)
    suspend fun addToFavorite(bookEntity: BookEntity)
}