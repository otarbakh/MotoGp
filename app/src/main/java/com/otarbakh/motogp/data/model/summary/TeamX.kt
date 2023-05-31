package com.otarbakh.motogp.data.model.summary

import com.otarbakh.motogp.domain.model.TeamDomain

data class TeamX(
    val competitors: List<CompetitorX>?,
    val country_code: String?,
    val gender: String?,
    val id: String,
    val name: String?,
    val nationality: String?,
    val result: ResultXX?
)

fun TeamX.toTeamDomain(): TeamDomain {
    return TeamDomain( country_code, id, name, nationality)
}