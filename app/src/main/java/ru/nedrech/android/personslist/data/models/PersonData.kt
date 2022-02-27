package ru.nedrech.android.personslist.data.models

abstract class PersonData(
    open val name: String,
    open val role: String,
    open val description: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PersonData

        return name == other.name && role == other.role && description == other.description
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}