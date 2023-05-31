package com.otarbakh.motogp.data.model.summary

data class Result(
    val bike_number: Int?,
    val fastest_laps: Int?,
    val podiums: Int?,
    val points: Int?,
    val pole_positions: Int?,
    val position: Int?,
    val races: Int?,
    val races_with_points: Int?,
    val victories: Int?
)