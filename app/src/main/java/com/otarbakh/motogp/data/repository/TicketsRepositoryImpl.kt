package com.otarbakh.motogp.data.repository

import com.otarbakh.motogp.data.database.TicketsDao
import com.otarbakh.motogp.data.model.TicketsEntity
import com.otarbakh.motogp.domain.repository.TicketsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TicketsRepositoryImpl @Inject constructor(
    private val ticketsDao: TicketsDao
) : TicketsRepository{
    override fun getTickets(): Flow<List<TicketsEntity>> {
        return ticketsDao.getAll()
    }

    override suspend fun insertTicket(ticket: TicketsEntity) {
        ticketsDao.insert(ticket)
    }

    override suspend fun deleteTicket(ticket: TicketsEntity) {
        ticketsDao.delete(ticket)
    }

    override suspend fun deleteAll() {
        ticketsDao.deleteAll()
    }
}