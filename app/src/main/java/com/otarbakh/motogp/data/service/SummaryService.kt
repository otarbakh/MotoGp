package com.otarbakh.motogp.data.service

import com.otarbakh.motogp.common.Constants
import com.otarbakh.motogp.data.summary.SummaryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SummaryService {
    @GET(Constants.SUMMARY_URL_END_POINT)
    suspend fun getSummary(
        @Query("api_key")
        apiKey: String
    ):Response<SummaryDto>

}