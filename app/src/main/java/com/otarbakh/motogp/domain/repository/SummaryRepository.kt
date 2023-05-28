package com.otarbakh.motogp.domain.repository

import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.data.summary.Stage
import kotlinx.coroutines.flow.Flow

interface SummaryRepository {

    suspend fun getSummary(): Flow<Resource<Stage>>
}