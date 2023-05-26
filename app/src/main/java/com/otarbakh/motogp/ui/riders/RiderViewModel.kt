package com.otarbakh.motogp.ui.riders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.data.summary.StageX
import com.otarbakh.motogp.domain.use_case.RidersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RiderViewModel @Inject constructor(
    private val ridersUseCase: RidersUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<List<StageX>>> (Resource.Loading(false))
    val state = _state.asStateFlow()
    fun getRiders(){
        ridersUseCase().onEach { result ->
            when (result){
                is Resource.Success -> _state.value = Resource.Success(result.data)
                is Resource.Error -> _state.value = Resource.Error("woops!")
                is Resource.Loading -> _state.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }
}