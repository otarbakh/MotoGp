package com.otarbakh.motogp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.otarbakh.motogp.data.model.summary.CompetitorX
import com.otarbakh.motogp.data.model.summary.ResultXX
import com.otarbakh.motogp.domain.model.TeamDomain


@Entity(tableName = "MotoGpTeams")
data class TeamsEntity(


    val country_code: String?,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String?,
    val nationality: String?,

)
fun TeamsEntity.toTeamDomain(): TeamDomain{
    return TeamDomain( country_code, id, name, nationality)
}