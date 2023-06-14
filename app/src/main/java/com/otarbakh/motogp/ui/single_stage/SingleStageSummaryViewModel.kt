package com.otarbakh.motogp.ui.single_stage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.data.model.single_stage_summary.Competitor
import com.otarbakh.motogp.data.model.summary.Stage
import com.otarbakh.motogp.data.repository.SummaryRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleStageSummaryViewModel @Inject constructor(
    private val singleStageSummaryRepositoryImpl: SummaryRepositoryImpl,
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<List<Competitor>?>>(Resource.Loading(false))
    val state = _state.asStateFlow()

    fun getSingleStageSummary(stageId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            singleStageSummaryRepositoryImpl.getSingleStageSummary(stageId).collectLatest {
                when(it){

                    is Resource.Success -> _state.value = Resource.Success(it.data)
                    is Resource.Error -> _state.value = Resource.Error("woops!")
                    is Resource.Loading -> _state.value = Resource.Loading(true)
                }
            }
        }

    }
}