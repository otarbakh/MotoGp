package com.otarbakh.motogp.data.model.single_stage_summary

data class Competitor(
    val country_code: String?,
    val gender: String?,
    val id: String?,
    val name: String?,
    val nationality: String?,
    val result: Result?,
    val team: Team?
)