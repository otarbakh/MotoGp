package com.otarbakh.motogp.data.repository.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.otarbakh.motogp.common.Constants.NETWORK_PAGE_SIZE
import com.otarbakh.motogp.domain.model.ArticleDomain
import com.otarbakh.motogp.data.service.NewsService
import com.otarbakh.motogp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val api: NewsService,
): NewsRepository {

    override fun getNews(q:String): Flow<PagingData<ArticleDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsDataSource(api,q) }
        ).flow
    }
}