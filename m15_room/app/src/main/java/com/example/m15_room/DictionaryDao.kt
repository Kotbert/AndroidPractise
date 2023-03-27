package com.example.m15_room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {

    @Query("SELECT * FROM dictionary")
    fun getAllWord(): Flow<List<Dictionary>>

    @Insert(entity = Dictionary::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dictionary: Dictionary)

    @Delete
    suspend fun delete(dictionary: Dictionary)

    @Update
    suspend fun update(dictionary: Dictionary)
}