package com.otarbakh.motogp.data.model.single_stage_summary

data class Parent(
    val category: Category?,
    val description: String?,
    val id: String?,
    val parents: List<ParentX>?,
    val scheduled: String?,
    val scheduled_end: String?,
    val single_event: Boolean?,
    val sport: Sport?,
    val type: String?
)