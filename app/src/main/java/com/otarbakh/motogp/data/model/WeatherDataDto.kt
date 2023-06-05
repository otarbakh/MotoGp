package com.otarbakh.motogp.data.model



import com.google.gson.annotations.SerializedName
import com.otarbakh.motogp.domain.model.WeatherDomain

data class WeatherDataDto(
    @SerializedName("daily")
    val daily: Daily = Daily(),
    @SerializedName("daily_units")
    val dailyUnits: DailyUnits = DailyUnits(),
    @SerializedName("elevation")
    val elevation: Double = 0.0,
    @SerializedName("generationtime_ms")
    val generationtimeMs: Double = 0.0,
    @SerializedName("latitude")
    val latitude: Double = 0.0,
    @SerializedName("longitude")
    val longitude: Double = 0.0,
    @SerializedName("timezone")
    val timezone: String = "",
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String = "",
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int = 0
) {
    data class Daily(
        @SerializedName("precipitation_sum")
        val precipitationSum: List<Double> = listOf(),
        @SerializedName("weathercode")
        val weatherCode: List<Int> = listOf(),
        @SerializedName("temperature_2m_max")
        val temperature2mMax: List<Double> = listOf(),
        @SerializedName("time")
        val time: List<String> = listOf()
    )


    data class DailyUnits(
        @SerializedName("precipitation_sum")
        val precipitationSum: String = "",
        @SerializedName("temperature_2m_max")
        val temperature2mMax: String = "",
        @SerializedName("time")
        val time: String = ""
    )

}

fun WeatherDataDto.Daily.toDomain(): WeatherDomain {
    return WeatherDomain(
        precipitationSum, weatherCode, temperature2mMax, time
    )
}
