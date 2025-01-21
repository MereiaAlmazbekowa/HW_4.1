package com.example.hw_41

import android.app.Application
import androidx.room.Room
import com.example.hw_41.data.db.DataBase
import com.example.hw_41.utils.PreferenceHelper

class App : Application() {

    companion object {
        var appDataBase: DataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.init(this)
        getInstance()
    }

    private fun getInstance(): DataBase? {
        if (appDataBase == null) {
            appDataBase = applicationContext?.let { context ->
                Room.databaseBuilder(
                    context,
                    DataBase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDataBase
    }
}
