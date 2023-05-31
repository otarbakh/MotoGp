package com.otarbakh.motogp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.otarbakh.motogp.data.model.TeamsEntity


@Database(entities = [TeamsEntity::class], version = 1)
abstract class DataBase: RoomDatabase(){
    abstract val teamsDao: TeamsDao
}