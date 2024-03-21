package com.zntkr.mybookapplication.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zntkr.mybookapplication.core.database.dao.BooksDao
import com.zntkr.mybookapplication.core.database.model.BookEntity

@Database(
    entities =[
        BookEntity::class
    ],
    version = 1
)


internal abstract class BooksDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao
}