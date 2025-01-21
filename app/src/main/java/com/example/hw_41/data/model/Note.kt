package com.example.hw_41.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class Note(
    val title: String,
    val description: String,
    val data: String,
    val color: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}