package com.otarbakh.motogp.data.model.single_stage_summary

data class Stage(
    val competitors: List<Competitor>?,
    val description: String?,
    val id: String?,
    val parents: List<Parent>?,
    val scheduled: String?,
    val scheduled_end: String?,
    val single_event: Boolean?,
    val stages: List<StageX>?,
    val status: String?,
    val teams: List<TeamX>?,
    val type: String?,
    val venue: Venue?
)