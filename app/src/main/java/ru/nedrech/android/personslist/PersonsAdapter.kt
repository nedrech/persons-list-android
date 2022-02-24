package ru.nedrech.android.personslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.nedrech.android.personslist.databinding.AdapterPersonsBinding

class PersonsAdapter : RecyclerView.Adapter<PersonsViewHolder>() {

    var persons = mutableListOf<Person>()

    fun setPersonsList(persons: List<Person>) {
        this.persons = persons.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterPersonsBinding.inflate(inflater, parent, false)
        return PersonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonsViewHolder, position: Int) {
        val person = persons[position]

        holder.binding.name.text = person.getFullName()
        holder.binding.role.text = person.role

        //Glide.with(holder.itemView.context).load(person.photoUrl).into(holder.binding.photo)
    }

    override fun getItemCount(): Int {
        return persons.size
    }
}

class PersonsViewHolder(val binding: AdapterPersonsBinding) : RecyclerView.ViewHolder(binding.root) {

}