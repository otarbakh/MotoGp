package com.otarbakh.motogp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ticket")
data class TicketsEntity(
    @PrimaryKey(autoGenerate = true)
    val ticketId:Int,
    val raceDay:String,
    val raceName:String
)
