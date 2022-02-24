package ru.nedrech.android.personslist.data.entities

data class Person(

    val firstName: String,

    val secondName: String,

    val middleName: String?,

    val role: String,

    val photoUrl: String?,

    val Description: String
) {
    fun getFullName() = "$secondName $firstName $middleName"
}
