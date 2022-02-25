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
}