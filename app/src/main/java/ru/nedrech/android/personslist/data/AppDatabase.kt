package ru.nedrech.android.personslist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nedrech.android.personslist.data.dao.PersonDao
import ru.nedrech.android.personslist.data.models.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}