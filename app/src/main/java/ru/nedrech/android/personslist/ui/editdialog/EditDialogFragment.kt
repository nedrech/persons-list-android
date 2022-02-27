package ru.nedrech.android.personslist.ui.editdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.nedrech.android.personslist.data.models.Person
import ru.nedrech.android.personslist.data.models.PersonArgConsts
import ru.nedrech.android.personslist.data.models.PersonArgConsts.Companion.DESC_ARG
import ru.nedrech.android.personslist.data.models.PersonArgConsts.Companion.NAME_ARG
import ru.nedrech.android.personslist.data.models.PersonArgConsts.Companion.ROLE_ARG
import ru.nedrech.android.personslist.data.models.PersonData
import ru.nedrech.android.personslist.databinding.EditDialogBinding

class EditDialogFragment(private val listener: OnSaveListener) : DialogFragment() {

    interface OnSaveListener {
        fun onSave(updatedData: PersonData)
    }

    companion object : PersonArgConsts() {
        fun show(
            fragmentManager: FragmentManager,
            personData: PersonData,
            listener: OnSaveListener
        ) = EditDialogFragment(listener)
            .apply {
                arguments = personData.getBundle()
            }
            .showNow(fragmentManager, null)
    }

    private lateinit var binding: EditDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditDialogBinding.inflate(inflater, container, false)

        initButtonListeners()
        initTextFields()

        return binding.root
    }

    private fun initButtonListeners() {
        binding.save.setOnClickListener { save() }
        binding.cancel.setOnClickListener { dismiss() }
    }

    private fun initTextFields() {
        arguments?.let {
            binding.name.setText(it.getString(NAME_ARG))
            binding.role.setText(it.getString(ROLE_ARG))
            binding.description.setText(it.getString(DESC_ARG))
        }
    }

    private fun save() {
        listener.onSave(personViewData)
        dismiss()
    }

    private val personViewData: PersonData by lazy {
        Person(
            binding.name.text.toString(), binding.role.text.toString(),
            binding.description.text.toString()
        )
    }
}