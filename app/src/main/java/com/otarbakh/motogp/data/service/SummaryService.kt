package com.otarbakh.motogp.data.service

import com.otarbakh.motogp.common.Constants
import com.otarbakh.motogp.data.model.single_stage_summary.SingleStageSummaryDto
import com.otarbakh.motogp.data.model.summary.SummaryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SummaryService {
    @GET(Constants.SUMMARY_URL_END_POINT)
    suspend fun fetchSummary(
        @Query("api_key")
        apiKey: String
    ):Response<SummaryDto>


    @GET("sport_events/{stage}/summary.json")
    suspend fun fetchSingleStageSummary(
        @Path("stage") stage: String,
        @Query("api_key") apiKey: String,
    ): Response<SingleStageSummaryDto>

}