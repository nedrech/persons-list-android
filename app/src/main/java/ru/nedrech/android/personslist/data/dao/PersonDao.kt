package ru.nedrech.android.personslist.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.nedrech.android.personslist.data.models.Person

@Dao
interface PersonDao {

    @Query("SELECT * FROM persons")
    fun getAll(): LiveData<List<Person>>

    @Insert
    suspend fun insertAll(persons: List<Person>)

    @Delete
    suspend fun delete(person: Person)

    @Update
    suspend fun update(person: Person)
}