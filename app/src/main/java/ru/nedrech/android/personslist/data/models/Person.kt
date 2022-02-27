package ru.nedrech.android.personslist.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(

    var name: String,

    var role: String,

    @ColumnInfo(name = "photo_url")
    val photoUrl: String?,

    var description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
