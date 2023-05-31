package com.otarbakh.motogp.ui.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.data.database.TeamsDao
import com.otarbakh.motogp.data.model.TeamsEntity
import com.otarbakh.motogp.data.model.summary.Stage
import com.otarbakh.motogp.data.model.summary.StageX
import com.otarbakh.motogp.data.model.summary.Team
import com.otarbakh.motogp.domain.model.TeamDomain
import com.otarbakh.motogp.domain.repository.SummaryRepository
import com.otarbakh.motogp.domain.use_case.TeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val summaryRepository: SummaryRepository,
    private val teamsUseCase: TeamsUseCase,
) : ViewModel(){
    private val _state = MutableStateFlow<Resource<List<TeamDomain>>>(Resource.Loading(false))
    val state = _state.asStateFlow()


    fun getTeams(){
        teamsUseCase().onEach {
            result ->
            when(result){
                is Resource.Success -> _state.value = Resource.Success(result.data)
                is Resource.Error -> _state.value = Resource.Error("woops!")
                is Resource.Loading -> _state.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }
    suspend fun addTeam(team: TeamDomain){
        summaryRepository.insert(team)
    }
}