package com.otarbakh.motogp.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otarbakh.motogp.data.model.TeamsEntity
import com.otarbakh.motogp.domain.model.TeamDomain
import com.otarbakh.motogp.domain.repository.SummaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val summaryRepository: SummaryRepository,
) : ViewModel() {

    fun deleteTeam(team: TeamDomain){
        viewModelScope.launch(Dispatchers.IO) {
            summaryRepository.deleteTeam(team)
        }
    }
    fun addTeam(team: TeamDomain){
        viewModelScope.launch(Dispatchers.IO) {
            summaryRepository.insert(team)
        }
    }


    suspend fun getTeam(): Flow<List<TeamDomain>> {
        return summaryRepository.getTeams()
    }

}