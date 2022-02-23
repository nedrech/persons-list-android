package ru.nedrech.android.personslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.nedrech.android.personslist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.mainFrame.id, MainFragment.newInstance())
                .commitNow()
        }
    }
}