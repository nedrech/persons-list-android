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

class PersonsFragment : Fragment() {

    companion object {
        fun newInstance() = PersonsFragment()
    }

    private lateinit var viewModel: PersonsViewModel

    private lateinit var binding: FragmentPersonsBinding

    private lateinit var adapter: PersonsAdapter

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
        viewModel.allPersons.observe(viewLifecycleOwner) { persons ->
            adapter.items = persons.toMutableList()
        }
    }

    private fun initSwipes() {
        ItemTouchHelper(PersonsSwipeHelper(viewModel, adapter))
            .attachToRecyclerView(binding.recyclerView)
    }
}