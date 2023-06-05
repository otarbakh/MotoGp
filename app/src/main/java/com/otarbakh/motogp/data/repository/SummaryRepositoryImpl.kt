package com.otarbakh.motogp.data.repository

import android.util.Log
import com.otarbakh.motogp.common.Constants
import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.data.database.TeamsDao
import com.otarbakh.motogp.data.model.TeamsEntity
import com.otarbakh.motogp.data.service.SummaryService
import com.otarbakh.motogp.data.model.summary.Stage
import com.otarbakh.motogp.data.model.toDomain
import com.otarbakh.motogp.data.model.toTeamDomain
import com.otarbakh.motogp.data.service.WeatherService
import com.otarbakh.motogp.domain.model.TeamDomain
import com.otarbakh.motogp.domain.model.WeatherDomain
import com.otarbakh.motogp.domain.model.toTeamEntity
import com.otarbakh.motogp.domain.repository.SummaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException


import javax.inject.Inject

class SummaryRepositoryImpl @Inject constructor(
    private val summaryService: SummaryService,
    private val favouritesDao: TeamsDao,
    private val weatherService: WeatherService
) : SummaryRepository {
    override suspend fun getSummary(): Flow<Resource<Stage>> = flow {
        try {
            emit(Resource.Loading(true))
            val response = summaryService.fetchSummary(Constants.APY_KEY)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.stage!!))

                Log.d("Jameson", "${response}")
            } else {
                Log.d("Jameson", "erorrrrrrrr")
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Ops Erorr"))
            Log.d("Jameson", "erorr")
        } catch (e: HttpException) {
            emit(Resource.Error(e.message()))

            Log.d("Jameson", "eoriraa vaax")
        }
    }

    override suspend fun getTeams(): Flow<List<TeamDomain>> {
        return favouritesDao.getAll().map { it.map { it.toTeamDomain() } }
    }

    override suspend fun insert(team: TeamDomain) {
        favouritesDao.insert(team.toTeamEntity())
    }

    override suspend fun deleteTeam(team: TeamsEntity) {
        favouritesDao.delete(team)
    }

    override suspend fun deleteAll() {
        favouritesDao.deleteAll()
    }


    override suspend fun getWeather(lat: Double, long: Double): Flow<Resource<WeatherDomain>>  = flow {
        try {
            emit(Resource.Loading(true))
            val response = weatherService.getForecast(lat,long,listOf("temperature_2m_max","precipitation_sum","weathercode"),"Europe/Moscow")
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.daily.toDomain()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message.toString()))
        }
    }

}
