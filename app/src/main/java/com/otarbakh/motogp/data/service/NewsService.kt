package com.otarbakh.motogp.data.service


import com.otarbakh.motogp.data.model.news.MotoGpNewsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    suspend fun getNews(
        @Query("q")
        q: String,
        @Query("page")
        page:Int,
        @Query("pageSize")
        pageSize:Int,
        @Query("apiKey")
        apiKey: String,
    ): Response<MotoGpNewsDto>
}