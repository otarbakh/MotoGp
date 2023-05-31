package com.otarbakh.motogp.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otarbakh.motogp.data.model.TeamsEntity
import com.otarbakh.motogp.domain.repository.SummaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val summaryRepository: SummaryRepository,
) : ViewModel() {

    private var _state  = MutableStateFlow<List<TeamsEntity>>(emptyList())
    val state = _state.asStateFlow()

    fun getFavouritesTeams(){
        viewModelScope.launch(Dispatchers.IO) {
            _state = summaryRepository.getFavouritismTeams() as MutableStateFlow<List<TeamsEntity>>

        }
    }

}