package com.tsu.codewars2.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [DataEntity::class], version = 6)
abstract class AppDatabase:RoomDatabase() {

    abstract fun getDataDao(): DataDao

}

