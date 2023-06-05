package com.otarbakh.motogp.data.service


import com.otarbakh.motogp.data.model.WeatherDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    suspend fun getForecast(
        @Query("latitude")
        latitude: Double,
        @Query("longitude")
        longitude: Double,
        @Query("daily")
        daily: List<String>,
        @Query("timezone")
        timezone: String,
    ): Response<WeatherDataDto>

}