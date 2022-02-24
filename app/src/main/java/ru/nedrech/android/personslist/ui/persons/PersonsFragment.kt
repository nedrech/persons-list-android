package ru.nedrech.android.personslist.ui.persons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nedrech.android.personslist.databinding.FragmentPersonsBinding
import ru.nedrech.android.personslist.util.observeOnce

class PersonsFragment : Fragment() {

    companion object {
        fun newInstance() = PersonsFragment()
    }

    lateinit var viewModel: PersonsViewModel

    lateinit var binding: FragmentPersonsBinding

    lateinit var adapter: PersonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonsBinding.inflate(inflater, container, false)

        initRecyclerView()
        initViewModel()
        initSwipes()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.insert()
    }

    private fun initRecyclerView()
    {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = PersonsAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun initViewModel()
    {
        viewModel = ViewModelProvider(this)[PersonsViewModel::class.java]
        viewModel.allPersons.observeOnce(viewLifecycleOwner) { persons ->
            adapter.items = persons.toMutableList()
        }
    }

    private fun initSwipes() {
        ItemTouchHelper(PersonsSwipeHelper(this))
            .attachToRecyclerView(binding.recyclerView)
    }
}