package ru.nedrech.android.personslist.ui.persons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nedrech.android.personslist.data.entities.Person
import ru.nedrech.android.personslist.databinding.FragmentPersonsBinding

class PersonsFragment : Fragment() {

    companion object {
        fun newInstance() = PersonsFragment()
    }

    private lateinit var viewModel: PersonsViewModel

    private lateinit var binding: FragmentPersonsBinding

    private val adapter = PersonsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonsBinding.inflate(inflater, container, false)

        binding.recycler.layoutManager = LinearLayoutManager(activity)
        binding.recycler.adapter = this.adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PersonsViewModel::class.java)

        adapter.setPersonsList(listOf(
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas"),
            Person("Иван", "Иванов", "Иванович", "программист", null, "Asdasfasfas")
        ))

        adapter.notifyDataSetChanged()
    }
}