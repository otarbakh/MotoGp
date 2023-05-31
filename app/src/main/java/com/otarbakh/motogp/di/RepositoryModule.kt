package com.otarbakh.motogp.di

import com.otarbakh.motogp.data.repository.SummaryRepositoryImpl
import com.otarbakh.motogp.domain.repository.SummaryRepository
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
    abstract fun provideTeamsRepo()
}


