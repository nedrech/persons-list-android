package ru.nedrech.android.personslist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.nedrech.android.personslist.databinding.MainActivityBinding
import ru.nedrech.android.personslist.ui.persons.PersonsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBar()

        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .replace(binding.mainFrame.id, PersonsFragment.newInstance())
                .commitNow()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun initToolBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}