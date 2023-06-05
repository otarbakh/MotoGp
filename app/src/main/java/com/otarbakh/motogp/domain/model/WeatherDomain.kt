package com.otarbakh.motogp.domain.model

data class WeatherDomain (
    val precipitationSum: List<Double> = listOf(),
    val weatherCode: List<Int> = listOf(),
    val temperature2mMax: List<Double> = listOf(),
    val time: List<String> = listOf()
)
