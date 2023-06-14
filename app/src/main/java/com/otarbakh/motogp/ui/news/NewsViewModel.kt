package com.otarbakh.motogp.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.otarbakh.motogp.data.repository.news.NewsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepo: NewsRepositoryImpl
) : ViewModel() {

    private val _currentQuery = MutableStateFlow(DEFAULT_QUERY)
    val currentQuery = _currentQuery.asStateFlow()

    val news = currentQuery.flatMapLatest {query ->
        newsRepo.getNews(query).cachedIn(viewModelScope)
    }

    fun searchNews(query:String){
        _currentQuery.value = query
    }
    companion object {
        private const val DEFAULT_QUERY = "MotoGp"
    }
}