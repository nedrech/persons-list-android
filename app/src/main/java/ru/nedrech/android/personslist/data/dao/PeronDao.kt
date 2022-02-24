package ru.nedrech.android.personslist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.nedrech.android.personslist.data.models.Person

@Dao
interface PeronDao {

    @Query("SELECT * FROM persons")
    fun getAll(): List<Person>

    @Query("SELECT * FROM persons WHERE id = :id")
    fun getById(id: Int): Person

    @Insert
    fun insertAll(vararg persons: Person)

    @Delete
    fun delete(person: Person)
}