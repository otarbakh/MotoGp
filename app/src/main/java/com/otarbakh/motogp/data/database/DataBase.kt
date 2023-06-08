package com.otarbakh.motogp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.otarbakh.motogp.data.model.TeamsEntity
import com.otarbakh.motogp.data.model.TicketsEntity


@Database(entities = [TeamsEntity::class, TicketsEntity::class], version = 3)
abstract class DataBase: RoomDatabase(){
    abstract val teamsDao: TeamsDao
    abstract val ticketsDao: TicketsDao
}