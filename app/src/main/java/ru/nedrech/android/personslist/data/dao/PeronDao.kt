package ru.nedrech.android.personslist.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.nedrech.android.personslist.data.models.Person

@Dao
interface PeronDao {

    @Query("SELECT * FROM persons")
    fun getAll(): LiveData<List<Person>>

    @Query("SELECT * FROM persons WHERE id = :id LIMIT 1")
    fun getById(id: Int): Person

    @Insert
    fun insertAll(persons: List<Person>)

    @Delete
    fun delete(person: Person)

    @Update
    fun update(person: Person)
}