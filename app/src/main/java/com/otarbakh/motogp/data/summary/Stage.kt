package com.otarbakh.motogp.data.summary

data class Stage(
    val category: Category?,
    val competitors: List<Competitor>?,
    val description: String?,
    val id: String?,
    val parents: List<Parent>?,
    val scheduled: String?,
    val scheduled_end: String?,
    val single_event: Boolean?,
    val sport: Sport?,
    val stages: List<StageX>?,
    val teams: List<TeamX>?,
    val type: String?
)