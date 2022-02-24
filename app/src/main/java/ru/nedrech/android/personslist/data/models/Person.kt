package ru.nedrech.android.personslist.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "second_name")
    val secondName: String,

    @ColumnInfo(name = "middle_name")
    val middleName: String?,

    val role: String,

    @ColumnInfo(name = "photo_url")
    val photoUrl: String?,

    val description: String
) {
    constructor(
        firstName: String,
        secondName: String,
        middleName: String?,
        role: String,
        photoUrl: String?,
        description: String
    ) : this(Int.MIN_VALUE, firstName, secondName, middleName, role, photoUrl, description)

    fun getFullName() = "$secondName $firstName $middleName"
}
