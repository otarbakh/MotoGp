package com.otarbakh.motogp.data.summary

data class StageX(
    val description: String?,
    val id: String?,
    val scheduled: String?,
    val scheduled_end: String?,
    val single_event: Boolean?,
    val status: String?,
    val type: String?,
    val venue: Venue?
)