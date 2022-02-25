package ru.nedrech.android.personslist.ui.persons

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nedrech.android.personslist.App
import ru.nedrech.android.personslist.data.models.Person
import ru.nedrech.android.personslist.data.repositories.PersonRepository

class PersonsViewModel : ViewModel() {

    val allPersons: LiveData<List<Person>>

    private val repository: PersonRepository

    init {
        val personDao = App.database.personDao()
        repository = PersonRepository(personDao)
        allPersons = repository.getAll()
    }

    fun insert() {
        viewModelScope.launch {
            repository.insertMany(listOf(Person("Иван", "Иванов", "Иванович", "программист", null, "ASdasdas")))
        }
    }

    fun delete(person: Person) {
        viewModelScope.launch {
            repository.delete(person)
        }
    }

    private fun deleteMany(persons: List<Person>) {
        viewModelScope.launch {
            repository.deleteMany(persons)
        }
    }

    fun deleteVarious(persons: List<Person>) {
        val dbPersons = allPersons.value!!

        val sum = persons + dbPersons

        val difference = sum.groupBy { it.id }
            .filter { it.value.size == 1 }
            .flatMap { it.value }

        deleteMany(difference)
    }
}