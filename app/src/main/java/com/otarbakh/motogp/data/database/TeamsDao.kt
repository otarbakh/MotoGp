package com.otarbakh.motogp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.otarbakh.motogp.data.model.TeamsEntity
import com.otarbakh.motogp.domain.model.TeamDomain
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamsDao {
    @Query("SELECT * FROM MotoGpTeams")
    fun getAll(): Flow<List<TeamsEntity>>

    @Query("SELECT * FROM MotoGpTeams WHERE id IN (:teamId)")
    fun loadAllByIds(teamId: IntArray): Flow<List<TeamsEntity>>

    @Query("SELECT * FROM MotoGpTeams WHERE name LIKE :first AND " +
            "nationality LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Flow<TeamsEntity>

    @Insert
    fun insertAll(vararg teams: TeamsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teams: TeamsEntity)

    @Delete
    fun delete(team: TeamsEntity)

    @Query("DELETE FROM MotoGpTeams")
    fun deleteAll()

}