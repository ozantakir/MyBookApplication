package com.zntkr.mybookapplication.core.network

import com.zntkr.mybookapplication.core.network.model.BooksResponse

interface BookRetrofitDataSource {
    suspend fun getBooks(date: String, list: String): BooksResponse
}