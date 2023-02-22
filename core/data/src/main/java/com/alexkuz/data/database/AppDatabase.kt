package com.alexkuz.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alexkuz.data.database.dao.StubDao
import com.alexkuz.data.database.entity.StubEntity

private const val DATABASE_NAME = "stubDB.db"

@Database(entities = [StubEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun newInstance(context: Context) = Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    abstract fun stubDao(): StubDao
}