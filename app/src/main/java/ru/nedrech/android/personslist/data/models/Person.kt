package ru.nedrech.android.personslist.data.models

import android.os.Bundle
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(
    override var name: String,
    override var role: String,
    @ColumnInfo(name = "photo_url") val photoUrl: String?,
    override var description: String
) : PersonData(name, role, description) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(name: String, role: String, description: String) : this(
        name,
        role,
        null,
        description
    )

    fun applyData(data: PersonData) {
        if (name != data.name)
            name = data.name
        if (role != data.role)
            role = data.role
        if (description != data.description)
            description = data.description
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        if (!super.equals(other)) return false

        other as Person

        return name == other.name && role == other.role && description == other.description
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun getBundle(): Bundle = super.getBundle().apply {
        putString(PersonArgConsts.PHOT_ARG, photoUrl)
    }
}
