package com.otarbakh.motogp.domain.repository


import androidx.paging.PagingData
import com.otarbakh.motogp.domain.model.ArticleDomain
import kotlinx.coroutines.flow.Flow

interface NewsRepository{
    fun getNews(q:String): Flow<PagingData<ArticleDomain>>
}