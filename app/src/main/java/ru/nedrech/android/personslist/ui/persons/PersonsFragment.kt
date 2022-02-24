package ru.nedrech.android.personslist.ui.persons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nedrech.android.personslist.databinding.FragmentPersonsBinding

class PersonsFragment : Fragment() {

    companion object {
        fun newInstance() = PersonsFragment()
    }

    private lateinit var viewModel: PersonsViewModel

    private lateinit var binding: FragmentPersonsBinding

    private lateinit var recyclerAdapter: PersonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonsBinding.inflate(inflater, container, false)

        recyclerAdapter = PersonsAdapter()

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PersonsViewModel::class.java)
    }
}