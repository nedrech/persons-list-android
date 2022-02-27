package ru.nedrech.android.personslist.ui.persons

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.nedrech.android.personslist.data.models.Person
import ru.nedrech.android.personslist.databinding.PersonCardviewBinding
import ru.nedrech.android.personslist.ui.PersonActivity

class PersonsAdapter(private val context: Context) :
    RecyclerView.Adapter<PersonsAdapter.ViewHolder>() {

    var items = mutableListOf<Person>()
        set(value) {
            field = value
            notifyItemChanged(0, value.size)
        }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItem(position: Int, item: Person) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun updateItem(position: Int) {
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PersonCardviewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = items[position]

        holder.binding.name.text = person.name
        holder.binding.role.text = person.role
        Glide.with(holder.itemView.context).load(person.photoUrl).into(holder.binding.photo)

        holder.itemView.setOnClickListener {
            PersonActivity.show(context, person)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val binding: PersonCardviewBinding) : RecyclerView.ViewHolder(binding.root)
}