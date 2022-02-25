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

    fun ensureSeedData() {
        val seed = "Tation delenit percipitur at vix. Eam id posse dictas voluptua, veniam laoreet oportere no mea, quis regione suscipiantur mea an.\n" +
                "Eu cum iuvaret debitis voluptatibus, esse perfecto reformidans id has. Eu cum iuvaret debitis voluptatibus, esse perfecto reformidans id has. Elitr accommodare deterruisset eam te, vim munere pertinax consetetur at. Sale liber et vel. Ceteros assentior omittantur cum ad.\n" +
                "Nec labore cetero theophrastus no, ei vero facer veritus nec. Odio contentiones sed cu, usu commodo prompta prodesset id. Elitr accommodare deterruisset eam te, vim munere pertinax consetetur at. Elitr accommodare deterruisset eam te, vim munere pertinax consetetur at."

        insertMany(listOf(
            Person("Борисов Тимофей Святославович", "Орнитолог", "https://randomuser.me/api/portraits/med/men/75.jpg", seed),
            Person("Рудаков Игорь Михайлович", "Кинорежиссер", "https://randomuser.me/api/portraits/med/men/76.jpg", seed),
            Person("Федоров Захар Даниилович", "Машинист локомотива", "https://randomuser.me/api/portraits/med/men/77.jpg", seed),
            Person("Баранов Артём Александрович", "Тюремный надзиратель", "https://randomuser.me/api/portraits/med/men/78.jpg", seed),
            Person("Воронин Владислав Алексеевич", "Продюсер", "https://randomuser.me/api/portraits/med/men/79.jpg", seed),
            Person("Окулова София Максимовна", "Канонир", "https://randomuser.me/api/portraits/med/women/75.jpg", seed),
            Person("Левина Мария Дмитриевна", "Ботаник", "https://randomuser.me/api/portraits/med/women/76.jpg", seed),
            Person("Вавилов Марк Артёмович", "Инженер-лесотехник", "https://randomuser.me/api/portraits/med/men/80.jpg", seed),
            Person("Гончарова Софья Максимовна", "Журналист", "https://randomuser.me/api/portraits/med/women/77.jpg", seed),
            Person("Овчинникова Мелания Александровна", "Авиационный техник", "https://randomuser.me/api/portraits/med/women/78.jpg", seed),
            Person("Рябов Адам Макарович", "Каскадёр", "https://randomuser.me/api/portraits/med/men/81.jpg", seed),
            Person("Глебова Ева Артёмовна", "Виноградарь", "https://randomuser.me/api/portraits/med/women/79.jpg", seed),
            Person("Никитина Ксения Тихоновна", "Плиточник", "https://randomuser.me/api/portraits/med/women/80.jpg", seed),
            Person("Губанов Вадим Георгиевич", "Историк", "https://randomuser.me/api/portraits/med/men/82.jpg", seed),
            Person("Корнеева Варвара Ярославовна", "Хлебороб", "https://randomuser.me/api/portraits/med/women/81.jpg", seed),
            Person("Мальцев Ибрагим Алексеевич", "Морской пехотинец", "https://randomuser.me/api/portraits/med/men/83.jpg", seed),
            Person("Сорокин Леонид Артёмович", "Кинодраматург", "https://randomuser.me/api/portraits/med/men/84.jpg", seed),
            Person("Медведев Виталий Артёмович", "Лекальщик", "https://randomuser.me/api/portraits/med/men/85.jpg", seed),
            Person("Крылова Марьям Савельевна", "Кинолог", "https://randomuser.me/api/portraits/med/women/82.jpg", seed),
            Person("Морозов Дмитрий Никитич", "Художник", "https://randomuser.me/api/portraits/med/men/86.jpg", seed)
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