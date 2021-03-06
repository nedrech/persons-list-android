package ru.nedrech.android.personslist.data.repositories

import androidx.lifecycle.LiveData
import ru.nedrech.android.personslist.data.dao.PersonDao
import ru.nedrech.android.personslist.data.models.Person

class PersonRepository(private val personDao: PersonDao) {

    fun getAll(): LiveData<List<Person>> =
        personDao.getAll()

    suspend fun insertMany(persons: List<Person>) =
        personDao.insertMany(persons)

    suspend fun delete(person: Person) =
        personDao.delete(person)

    suspend fun deleteMany(persons: List<Person>) =
        personDao.deleteMany(persons)

    suspend fun update(person: Person) =
        personDao.update(person)
}