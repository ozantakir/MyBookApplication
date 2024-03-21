package com.zntkr.mybookapplication.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val publisher: String? = null,
    val description: String? = null,
    val title: String? = null,
    val author: String? = null,
    val bookImage: String? = null,
    val amazonProductUrl: String? = null,
    val rank: Int? = null,
    val isFavorite: Boolean = false
)
