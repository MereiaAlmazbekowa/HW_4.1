package com.example.hw_41.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw_41.data.db.dao.NoteDao
import com.example.hw_41.data.model.Note

@Database(entities = [Note::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}