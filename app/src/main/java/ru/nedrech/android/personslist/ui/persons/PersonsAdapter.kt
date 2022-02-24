package ru.nedrech.android.personslist.ui.persons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nedrech.android.personslist.data.models.Person
import ru.nedrech.android.personslist.databinding.AdapterPersonsBinding

class PersonsAdapter : RecyclerView.Adapter<PersonsAdapter.ViewHolder>() {

    var items = mutableListOf<Person>()
        set(value) {
            field = value
            notifyItemChanged(0, value.size)
        }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterPersonsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = items[position]

        holder.binding.name.text = person.fullName
        holder.binding.role.text = person.role
        //Glide.with(holder.itemView.context).load(person.photoUrl).into(holder.binding.photo)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val binding: AdapterPersonsBinding) : RecyclerView.ViewHolder(binding.root)
}