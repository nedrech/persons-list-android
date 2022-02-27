package ru.nedrech.android.personslist.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import ru.nedrech.android.personslist.data.models.Person
import ru.nedrech.android.personslist.databinding.PersonActivityBinding

class PersonActivity : AppCompatActivity() {

    companion object {
        private const val NAME_ARG = "nameArg"
        private const val ROLE_ARG = "roleArg"
        private const val DESC_ARG = "descArg"
        private const val PHOTO_URL_ARG = "photoUrlArg"

        fun show(context: Context, person: Person) =
            context.startActivity(
                Intent(context, PersonActivity::class.java).apply {
                    putExtra(NAME_ARG, person.name)
                    putExtra(ROLE_ARG, person.role)
                    putExtra(DESC_ARG, person.description)
                    putExtra(PHOTO_URL_ARG, person.photoUrl)
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
        Glide.with(this).load(intent.getStringExtra(PHOTO_URL_ARG)).into(binding.photo)
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