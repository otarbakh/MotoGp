package com.otarbakh.motogp.domain.repository

import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.data.model.TeamsEntity
import com.otarbakh.motogp.data.model.single_stage_summary.Competitor
import com.otarbakh.motogp.data.model.summary.Stage
import com.otarbakh.motogp.domain.model.TeamDomain
import com.otarbakh.motogp.domain.model.WeatherDomain
import kotlinx.coroutines.flow.Flow

interface SummaryRepository {

    suspend fun getSummary(): Flow<Resource<Stage>>


    suspend fun getSingleStageSummary(stageId:String): Flow<Resource<List<Competitor>?>>

    suspend fun getTeams() : Flow<List<TeamDomain>>

    suspend fun deleteTeam(team: TeamDomain)


    suspend fun insert(team: TeamDomain)

    suspend fun deleteAll()

    suspend fun getWeather(lat: Double, long: Double) : Flow<Resource<WeatherDomain>>
}