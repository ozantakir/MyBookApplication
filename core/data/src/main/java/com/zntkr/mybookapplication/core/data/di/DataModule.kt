package com.zntkr.mybookapplication.core.data.di

import com.zntkr.mybookapplication.core.data.repository.BookRepository
import com.zntkr.mybookapplication.core.data.repository.BookRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    internal abstract fun bindsBookRepository(
        bookRepository: BookRepositoryImpl,
    ): BookRepository
}