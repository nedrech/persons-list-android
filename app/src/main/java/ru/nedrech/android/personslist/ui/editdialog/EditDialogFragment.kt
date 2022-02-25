package ru.nedrech.android.personslist.ui.editdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import ru.nedrech.android.personslist.databinding.EditDialogBinding
import ru.nedrech.android.personslist.ui.persons.PersonsViewModel

class EditDialogFragment() : DialogFragment() {

    companion object {
        val TAG = null

        private const val NAME_ARG = "nameArg"
        private const val ROLE_ARG = "roleArg"
        private const val DESC_ARG = "descArg"

        fun newInstance(name: String, role: String, description: String) =
            EditDialogFragment()
                .apply { arguments = bundleOf(
                    NAME_ARG to name,
                    ROLE_ARG to role,
                    DESC_ARG to description
                )}
    }

    private var name: String? = null

    private var role: String? = null

    private var description: String? = null

    private lateinit var binding: EditDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME_ARG)
            role = it.getString(ROLE_ARG)
            description = it.getString(DESC_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditDialogBinding.inflate(inflater, container, false)

        binding.save.setOnClickListener { save() }
        binding.cancel.setOnClickListener { dismiss() }

        binding.name.setText(name)
        binding.role.setText(role)
        binding.description.setText(description)

        return binding.root
    }

    private fun save()
    {

    }
}