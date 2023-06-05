package com.otarbakh.motogp.domain.use_case

import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.data.repository.SummaryRepositoryImpl
import com.otarbakh.motogp.domain.model.WeatherDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val repository: SummaryRepositoryImpl,
){
    operator fun invoke(lat:Double,long:Double): Flow<Resource<WeatherDomain>> = channelFlow {
        repository.getWeather(lat, long).collectLatest {
            when (it){
                is Resource.Success -> {
                    send(Resource.Success(it.data))
                }
                is Resource.Error -> {
                    send(Resource.Error(it.error))
                }
                is Resource.Loading -> {
                    send(Resource.Loading(it.loading))
                }
            }
        }
    }
}