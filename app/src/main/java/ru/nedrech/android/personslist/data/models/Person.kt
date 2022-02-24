package ru.nedrech.android.personslist.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(

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
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @Ignore
    val fullName: String = "$secondName $firstName $middleName"
}
