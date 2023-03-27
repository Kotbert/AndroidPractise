package com.example.m15_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class Dictionary(
    @PrimaryKey @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "count") var count: Int = 1
)
