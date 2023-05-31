package com.otarbakh.motogp.domain.repository

import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.data.model.TeamsEntity
import com.otarbakh.motogp.data.model.summary.Stage
import com.otarbakh.motogp.domain.model.TeamDomain
import kotlinx.coroutines.flow.Flow

interface SummaryRepository {

    suspend fun getSummary(): Flow<Resource<Stage>>

    suspend fun getFavouritismTeams() : Flow<List<TeamDomain>>

    suspend fun deleteTeam(team: TeamsEntity)


    suspend fun insert(team: TeamDomain)

    suspend fun deleteAll()
}