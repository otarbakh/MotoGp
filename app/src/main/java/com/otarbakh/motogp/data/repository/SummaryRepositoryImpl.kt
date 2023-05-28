package com.otarbakh.motogp.data.repository

import android.util.Log
import com.otarbakh.motogp.common.Constants
import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.data.service.SummaryService
import com.otarbakh.motogp.data.summary.Stage
import com.otarbakh.motogp.domain.repository.SummaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException



import javax.inject.Inject

class SummaryRepositoryImpl @Inject constructor(
    private val summaryService: SummaryService
) : SummaryRepository {
    override suspend fun getSummary(): Flow<Resource<Stage>> = flow {
        try {
            emit(Resource.Loading(true))
            val response = summaryService.fetchSummary(Constants.APY_KEY)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.stage!!))

                Log.d("Jameson", "${response}")
            } else{
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
}
