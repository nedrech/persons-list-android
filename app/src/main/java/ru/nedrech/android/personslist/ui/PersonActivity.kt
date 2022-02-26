package ru.nedrech.android.personslist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.nedrech.android.personslist.R
import ru.nedrech.android.personslist.databinding.PersonActivityBinding

class PersonActivity : AppCompatActivity() {

    private lateinit var binding: PersonActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = PersonActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}