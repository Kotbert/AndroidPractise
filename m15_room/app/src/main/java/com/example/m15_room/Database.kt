package com.example.m15_room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Dictionary::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
}