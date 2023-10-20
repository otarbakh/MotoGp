package com.otarbakh.motogp.data.model.news


import com.google.gson.annotations.SerializedName


data class MotoGpNewsDto(
    @SerializedName("articles")
    val articles: List<Article> = listOf(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0


)


