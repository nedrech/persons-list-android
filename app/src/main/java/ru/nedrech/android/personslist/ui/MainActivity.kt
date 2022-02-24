package ru.nedrech.android.personslist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.nedrech.android.personslist.databinding.ActivityMainBinding
import ru.nedrech.android.personslist.ui.persons.PersonsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
}