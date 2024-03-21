package com.zntkr.mybookapplication.core.database

import com.zntkr.mybookapplication.core.database.dao.BooksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesBooksDao(
        database: BooksDatabase,
    ): BooksDao = database.booksDao()
}