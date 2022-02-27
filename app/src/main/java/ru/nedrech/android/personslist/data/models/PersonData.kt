package ru.nedrech.android.personslist.data.models

import androidx.core.os.bundleOf
import ru.nedrech.android.personslist.data.models.PersonArgConsts.Companion.DESC_ARG
import ru.nedrech.android.personslist.data.models.PersonArgConsts.Companion.NAME_ARG
import ru.nedrech.android.personslist.data.models.PersonArgConsts.Companion.ROLE_ARG

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

    open fun getBundle() = bundleOf(
        NAME_ARG to name,
        ROLE_ARG to role,
        DESC_ARG to description
    )
}