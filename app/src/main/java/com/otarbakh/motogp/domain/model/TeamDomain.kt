package com.otarbakh.motogp.domain.model

import com.otarbakh.motogp.data.model.TeamsEntity
import com.otarbakh.motogp.data.model.summary.CompetitorX
import com.otarbakh.motogp.data.model.summary.ResultXX

data class TeamDomain(
    val competitors: List<CompetitorX>?,
    val country_code: String?,
    val id: String?,
    val name: String?,
    val nationality: String?,

)

fun TeamDomain.toTeamEntity():TeamsEntity{
    return TeamsEntity(competitors, country_code, id, name, nationality)
}