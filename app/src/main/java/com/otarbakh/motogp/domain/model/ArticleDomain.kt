package com.otarbakh.motogp.domain.model

import com.otarbakh.motogp.data.model.news.Source


data class ArticleDomain(
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val title: String? = "",
    val urlToImage: String? = "",
    val url: String? = "",
    val source: Source
)
