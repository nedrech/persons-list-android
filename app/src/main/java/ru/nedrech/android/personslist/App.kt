package ru.nedrech.android.personslist

import android.app.Application
import androidx.room.Room
import ru.nedrech.android.personslist.data.AppDatabase

class App : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app-database"
        ).build()
    }
}