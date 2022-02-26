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

        insertMany(listOf(
            Person("Борисов Тимофей Святославович", "Орнитолог", "https://randomuser.me/api/portraits/med/men/75.jpg", filler),
            Person("Рудаков Игорь Михайлович", "Кинорежиссер", "https://randomuser.me/api/portraits/med/men/76.jpg", filler),
            Person("Федоров Захар Даниилович", "Машинист локомотива", "https://randomuser.me/api/portraits/med/men/77.jpg", filler),
            Person("Баранов Артём Александрович", "Тюремный надзиратель", "https://randomuser.me/api/portraits/med/men/78.jpg", filler),
            Person("Воронин Владислав Алексеевич", "Продюсер", "https://randomuser.me/api/portraits/med/men/79.jpg", filler),
            Person("Окулова София Максимовна", "Канонир", "https://randomuser.me/api/portraits/med/women/75.jpg", filler),
            Person("Левина Мария Дмитриевна", "Ботаник", "https://randomuser.me/api/portraits/med/women/76.jpg", filler),
            Person("Вавилов Марк Артёмович", "Инженер-лесотехник", "https://randomuser.me/api/portraits/med/men/80.jpg", filler),
            Person("Гончарова Софья Максимовна", "Журналист", "https://randomuser.me/api/portraits/med/women/77.jpg", filler),
            Person("Овчинникова Мелания Александровна", "Авиационный техник", "https://randomuser.me/api/portraits/med/women/78.jpg", filler),
            Person("Рябов Адам Макарович", "Каскадёр", "https://randomuser.me/api/portraits/med/men/81.jpg", filler),
            Person("Глебова Ева Артёмовна", "Виноградарь", "https://randomuser.me/api/portraits/med/women/79.jpg", filler),
            Person("Никитина Ксения Тихоновна", "Плиточник", "https://randomuser.me/api/portraits/med/women/80.jpg", filler),
            Person("Губанов Вадим Георгиевич", "Историк", "https://randomuser.me/api/portraits/med/men/82.jpg", filler),
            Person("Корнеева Варвара Ярославовна", "Хлебороб", "https://randomuser.me/api/portraits/med/women/81.jpg", filler),
            Person("Мальцев Ибрагим Алексеевич", "Морской пехотинец", "https://randomuser.me/api/portraits/med/men/83.jpg", filler),
            Person("Сорокин Леонид Артёмович", "Кинодраматург", "https://randomuser.me/api/portraits/med/men/84.jpg", filler),
            Person("Медведев Виталий Артёмович", "Лекальщик", "https://randomuser.me/api/portraits/med/men/85.jpg", filler),
            Person("Крылова Марьям Савельевна", "Кинолог", "https://randomuser.me/api/portraits/med/women/82.jpg", filler),
            Person("Морозов Дмитрий Никитич", "Художник", "https://randomuser.me/api/portraits/med/men/86.jpg", filler)
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