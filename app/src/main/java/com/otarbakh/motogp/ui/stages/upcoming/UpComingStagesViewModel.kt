package com.otarbakh.motogp.ui.stages.upcoming


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.data.model.summary.StageX
import com.otarbakh.motogp.domain.model.WeatherDomain
import com.otarbakh.motogp.domain.use_case.UpComingStagesUseCase
import com.otarbakh.motogp.domain.use_case.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UpComingStagesViewModel @Inject constructor(
    private val stagesUseCase: UpComingStagesUseCase,
    private val weatherUseCase: WeatherUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<Resource<List<StageX>?>>(Resource.Loading(false))
    val state = _state.asStateFlow()

    private val _weatherState = MutableStateFlow<Resource<WeatherDomain>>(Resource.Loading(false))
    val weatherState = _weatherState.asStateFlow()


    fun getStages() {
        stagesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> _state.value = Resource.Success(result.data)
                is Resource.Error -> _state.value = Resource.Error("woops!")
                is Resource.Loading -> _state.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }

    fun getWeather(lat: Double, long: Double){
        weatherUseCase(lat,long).onEach { result ->
            when (result) {
                is Resource.Success -> _weatherState.value = Resource.Success(result.data)
                is Resource.Error -> _weatherState.value = Resource.Error("woops!")
                is Resource.Loading -> _weatherState.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }
}