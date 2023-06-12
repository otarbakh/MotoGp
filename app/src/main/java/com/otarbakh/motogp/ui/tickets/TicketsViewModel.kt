package com.otarbakh.motogp.ui.tickets

import androidx.lifecycle.ViewModel
import com.otarbakh.motogp.data.model.TicketsEntity
import com.otarbakh.motogp.data.repository.TicketsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(

    private val ticketsRepositoryImpl: TicketsRepositoryImpl
)
    : ViewModel() {


    fun insertTicket(ticket: TicketsEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            ticketsRepositoryImpl.insertTicket(ticket)
        }
    }
}