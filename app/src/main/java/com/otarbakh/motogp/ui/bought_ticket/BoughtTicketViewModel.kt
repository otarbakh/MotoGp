package com.otarbakh.motogp.ui.bought_ticket

import androidx.lifecycle.ViewModel
import com.otarbakh.motogp.data.model.TicketsEntity
import com.otarbakh.motogp.data.repository.TicketsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoughtTicketViewModel @Inject constructor(
    private val ticketsRepositoryImpl: TicketsRepositoryImpl
) : ViewModel() {


     fun getTicket(): Flow<List<TicketsEntity>> {
        return ticketsRepositoryImpl.getTickets()
    }

    fun deleteTicket(ticket:TicketsEntity){
        CoroutineScope(Dispatchers.IO).launch {
            ticketsRepositoryImpl.deleteTicket(ticket)
        }
    }
}