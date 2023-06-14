package com.otarbakh.motogp.data.model.news



import com.google.gson.annotations.SerializedName
import com.otarbakh.motogp.domain.model.ArticleDomain

data class Article(
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("content")
    val content: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("publishedAt")
    val publishedAt: String? = "",
    @SerializedName("source")
    val source: Source = Source(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("urlToImage")
    val urlToImage: String? = ""
)

fun Article.toArticleDomain(): ArticleDomain {
    return ArticleDomain(
        content, description, publishedAt, title, urlToImage,url,source
    )
}