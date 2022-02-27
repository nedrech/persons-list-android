package ru.nedrech.android.personslist.ui.persons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nedrech.android.personslist.databinding.PersonsFragmentBinding

class PersonsFragment : Fragment() {

    lateinit var viewModel: PersonsViewModel

    lateinit var binding: PersonsFragmentBinding

    lateinit var adapter: PersonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PersonsFragmentBinding.inflate(inflater, container, false)

        initRecyclerView()
        initViewModel()
        initSwipes()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewModel.deleteVarious(adapter.items)
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = PersonsAdapter(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[PersonsViewModel::class.java]
        viewModel.allPersons.observe(viewLifecycleOwner) { persons ->
            if (persons.isEmpty())
                viewModel.seedDatabase()

            if (persons.isNotEmpty() && adapter.items.isEmpty()) {
                if (binding.spinner.isVisible) binding.spinner.visibility = View.INVISIBLE
                adapter.items = persons.toMutableList()
            }
        }
    }

    private fun initSwipes() {
        ItemTouchHelper(PersonsSwipeHelper(this))
            .attachToRecyclerView(binding.recyclerView)
    }
}