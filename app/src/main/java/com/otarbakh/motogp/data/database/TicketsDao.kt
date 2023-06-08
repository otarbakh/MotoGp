package com.otarbakh.motogp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.otarbakh.motogp.data.model.TicketsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TicketsDao {
    @Query("SELECT * FROM ticket")
    fun getAll(): Flow<List<TicketsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ticket: TicketsEntity)

    @Delete
    fun delete(ticket: TicketsEntity)

    @Query("DELETE FROM ticket")
    fun deleteAll()


}