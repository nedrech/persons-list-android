package ru.nedrech.android.personslist.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import ru.nedrech.android.personslist.data.models.Person
import ru.nedrech.android.personslist.data.models.PersonArgConsts.Companion.DESC_ARG
import ru.nedrech.android.personslist.data.models.PersonArgConsts.Companion.NAME_ARG
import ru.nedrech.android.personslist.data.models.PersonArgConsts.Companion.PHOT_ARG
import ru.nedrech.android.personslist.data.models.PersonArgConsts.Companion.ROLE_ARG
import ru.nedrech.android.personslist.databinding.PersonActivityBinding

class PersonActivity : AppCompatActivity() {

    companion object {
        fun show(context: Context, person: Person) =
            context.startActivity(
                Intent(context, PersonActivity::class.java).apply {
                    putExtras(person.getBundle())
                }
            )
    }

    private lateinit var binding: PersonActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = PersonActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        makeStatusBarTransparent()
        initToolBar()

        binding.name.text = intent.getStringExtra(NAME_ARG)
        binding.role.text = intent.getStringExtra(ROLE_ARG)
        binding.description.text = intent.getStringExtra(DESC_ARG)
        Glide.with(this).load(intent.getStringExtra(PHOT_ARG)).into(binding.photo)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun makeStatusBarTransparent() {
        window.statusBarColor = Color.TRANSPARENT
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun initToolBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }
}