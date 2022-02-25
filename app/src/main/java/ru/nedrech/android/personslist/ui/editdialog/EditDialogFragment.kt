package ru.nedrech.android.personslist.ui.editdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.nedrech.android.personslist.databinding.EditDialogBinding

class EditDialogFragment : DialogFragment() {

    lateinit var binding: EditDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditDialogBinding.inflate(inflater, container, false)

        return binding.root
    }
}