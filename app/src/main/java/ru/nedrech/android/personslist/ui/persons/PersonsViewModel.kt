package ru.nedrech.android.personslist.ui.persons

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nedrech.android.personslist.App
import ru.nedrech.android.personslist.data.models.Person
import ru.nedrech.android.personslist.data.repositories.PersonRepository
import java.util.*

class PersonsViewModel : ViewModel() {

    val allPersons: LiveData<List<Person>>

    private val repository: PersonRepository

    init {
        val personDao = App.database.personDao()
        repository = PersonRepository(personDao)
        allPersons = repository.getAll()
    }

    private fun insertMany(persons: List<Person>) {
        viewModelScope.launch {
            repository.insertMany(persons)
        }
    }

    fun delete(person: Person) {
        viewModelScope.launch {
            repository.delete(person)
        }
    }

    fun update(person: Person) {
        viewModelScope.launch {
            repository.update(person)
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

    fun seedDatabase(count: Int) {
        insertMany(
            MutableList(count) {
                Person(
                    "Иванов Иван Иванович",
                    "программист",
                    "https://i.pravatar.cc/600?u=${UUID.randomUUID()}",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur lacinia pulvinar purus vel sagittis. Etiam consectetur justo vitae dui viverra, ut finibus massa aliquam. Vivamus est justo, dignissim id diam ut, porta semper odio. Duis vestibulum volutpat odio, ut volutpat sapien bibendum in. Suspendisse at placerat eros. Aenean condimentum sem commodo, tincidunt mauris in, placerat massa. Sed placerat, dui feugiat eleifend tempor, quam diam consequat massa, id pulvinar nibh nibh nec libero. Etiam porttitor sagittis efficitur. Mauris et metus in odio ornare tincidunt.\n" +
                            "\n" +
                            "Vivamus volutpat mattis ex et rutrum. Donec ac accumsan neque, eu vehicula ipsum. Mauris risus nunc, hendrerit eget enim ac, finibus iaculis magna. Proin facilisis fringilla risus, ac posuere nisl commodo eget. In ex nibh, eleifend et velit sed, ultricies hendrerit magna. Duis ultricies felis tortor, eu vulputate lectus consectetur ut. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec condimentum erat non gravida semper."
                )
            }
        )
    }
}