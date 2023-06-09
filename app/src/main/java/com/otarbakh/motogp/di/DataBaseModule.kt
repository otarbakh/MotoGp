package com.otarbakh.motogp.di

import android.content.Context
import androidx.room.Room
import com.otarbakh.motogp.data.database.DataBase
import com.otarbakh.motogp.data.database.TeamsDao
import com.otarbakh.motogp.data.database.TicketsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides

    fun provideDb(@ApplicationContext context: Context):DataBase{
        return Room.databaseBuilder(
            context,
            DataBase::class.java,"TeamsTable",
        ).fallbackToDestructiveMigration()
            .build()
    }
    @Singleton
    @Provides
    fun provideTeamsDao(db: DataBase): TeamsDao {
        return db.teamsDao
    }

    @Singleton
    @Provides
    fun provideTicketsDao(db: DataBase): TicketsDao {
        return db.ticketsDao
    }
}