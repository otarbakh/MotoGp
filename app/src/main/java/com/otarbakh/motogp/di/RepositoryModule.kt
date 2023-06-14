package com.otarbakh.motogp.di

import com.otarbakh.motogp.data.repository.SummaryRepositoryImpl
import com.otarbakh.motogp.data.repository.TicketsRepositoryImpl
import com.otarbakh.motogp.data.repository.news.NewsRepositoryImpl
import com.otarbakh.motogp.domain.repository.NewsRepository
import com.otarbakh.motogp.domain.repository.SummaryRepository
import com.otarbakh.motogp.domain.repository.TicketsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSummaryRepository(
        summaryRepository: SummaryRepositoryImpl
    ):SummaryRepository

    @Binds
    @Singleton
    abstract fun provideTicketsRepo(repoImpl: TicketsRepositoryImpl): TicketsRepository


    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
   
}


