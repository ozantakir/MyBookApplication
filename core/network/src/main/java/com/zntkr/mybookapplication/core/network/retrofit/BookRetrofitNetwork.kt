package com.zntkr.mybookapplication.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.zntkr.mybookapplication.core.network.BookRetrofitDataSource
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton
import androidx.compose.ui.util.trace
import com.zntkr.mybookapplication.core.network.model.BooksResponse

interface BookRetrofitNetworkApi {
    @GET(value = "lists/{date}/{list}")
    suspend fun getBooks(
        @Path("date") date: String,
        @Path("list") list: String,
        @Query("api-key") apiKey: String = "2qPdAXqW5auN9LB2p2ycA1vlwarIXZ6g"
    ): NetworkResponse<BooksResponse>
}

private const val BOOK_BASE_URL = "https://api.nytimes.com/svc/books/v3/"

@Serializable
data class NetworkResponse<T>(
    val results: T
)

@Singleton
class BookRetrofitNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : BookRetrofitDataSource {

    private val networkApi = trace("BookRetrofitNetwork") {
        Retrofit.Builder()
            .baseUrl(BOOK_BASE_URL)
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(BookRetrofitNetworkApi::class.java)
    }

    override suspend fun getBooks(date: String, list: String): BooksResponse {
        return networkApi.getBooks(date, list).results
    }
}