package com.otarbakh.motogp.domain.use_case

import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.data.summary.Stage
import com.otarbakh.motogp.data.summary.StageX
import com.otarbakh.motogp.domain.repository.SummaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class StagesUseCase @Inject constructor(
    private val summaryRepository: SummaryRepository
){
    operator fun invoke(): Flow<Resource<Stage>> = channelFlow {
        summaryRepository.getSummary().collectLatest {
            when(it){
                is Resource.Success-> {
                    send(Resource.Success(it.data))
                }
                is Resource.Loading-> {
                    send(Resource.Loading(it.loading))
                }
                is Resource.Error-> {
                    send(Resource.Error(it.error))
                }
            }
        }
    }
}