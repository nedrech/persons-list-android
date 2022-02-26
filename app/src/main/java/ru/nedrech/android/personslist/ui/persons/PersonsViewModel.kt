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

    private fun deleteMany(persons: List<Person>) {
        viewModelScope.launch {
            repository.deleteMany(persons)
        }
    }

    fun seedDatabase() {
        val filler = "Tation delenit percipitur at vix. Eam id posse dictas voluptua, veniam laoreet oportere no mea, quis regione suscipiantur mea an.\n" +
                "Eu cum iuvaret debitis voluptatibus, esse perfecto reformidans id has. Eu cum iuvaret debitis voluptatibus, esse perfecto reformidans id has. Elitr accommodare deterruisset eam te, vim munere pertinax consetetur at. Sale liber et vel. Ceteros assentior omittantur cum ad.\n" +
                "Nec labore cetero theophrastus no, ei vero facer veritus nec. Odio contentiones sed cu, usu commodo prompta prodesset id. Elitr accommodare deterruisset eam te, vim munere pertinax consetetur at. Elitr accommodare deterruisset eam te, vim munere pertinax consetetur at."

        val photoUrl = "https://thispersondoesnotexist.com/image"

        insertMany(listOf(
            Person("Борисов Тимофей Святославович", "Орнитолог", "$photoUrl?1", filler),
            Person("Рудаков Игорь Михайлович", "Кинорежиссер", "$photoUrl?2", filler),
            Person("Федоров Захар Даниилович", "Машинист локомотива", "$photoUrl?3", filler),
            Person("Баранов Артём Александрович", "Тюремный надзиратель", "$photoUrl?4", filler),
            Person("Воронин Владислав Алексеевич", "Продюсер", "$photoUrl?5", filler),
            Person("Окулова София Максимовна", "Канонир", "$photoUrl?6", filler),
            Person("Левина Мария Дмитриевна", "Ботаник", "$photoUrl?7", filler),
            Person("Вавилов Марк Артёмович", "Инженер-лесотехник", "$photoUrl?8", filler),
            Person("Гончарова Софья Максимовна", "Журналист", "$photoUrl?9", filler),
            Person("Овчинникова Мелания Александровна", "Авиационный техник", "$photoUrl?10", filler),
            Person("Рябов Адам Макарович", "Каскадёр", "$photoUrl?11", filler),
            Person("Глебова Ева Артёмовна", "Виноградарь", "$photoUrl?12", filler),
            Person("Никитина Ксения Тихоновна", "Плиточник", "$photoUrl?13", filler),
            Person("Губанов Вадим Георгиевич", "Историк", "$photoUrl?14", filler),
            Person("Корнеева Варвара Ярославовна", "Хлебороб", "$photoUrl?15", filler),
            Person("Мальцев Ибрагим Алексеевич", "Морской пехотинец", "$photoUrl?16", filler),
            Person("Сорокин Леонид Артёмович", "Кинодраматург", "$photoUrl?17", filler),
            Person("Медведев Виталий Артёмович", "Лекальщик", "$photoUrl?18", filler),
            Person("Крылова Марьям Савельевна", "Кинолог", "$photoUrl?19", filler),
            Person("Морозов Дмитрий Никитич", "Художник", "$photoUrl?20", filler)
        ))
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