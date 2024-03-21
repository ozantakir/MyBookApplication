package com.zntkr.mybookapplication.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BooksResponse(
    val books: List<BookItem>
)

@Serializable
data class  BookItem(
    val uid: Int = 0,
    val rank: Int? = null,
    val publisher: String = "",
    val description: String = "",
    val title: String,
    val author: String,
    @SerialName("book_image") val bookImage: String,
    @SerialName("amazon_product_url") val amazonProductUrl: String,
    val isFavorite: Boolean = false
)