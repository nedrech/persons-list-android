package ru.nedrech.android.personslist.ui.persons

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.nedrech.android.personslist.data.models.Person
import ru.nedrech.android.personslist.databinding.PersonCardviewBinding
import ru.nedrech.android.personslist.databinding.SampleItemBinding
import ru.nedrech.android.personslist.ui.PersonActivity

class PersonsAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_1 = 0
        const val TYPE_2 = 1
    }

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

    private var counter = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)



        return when (viewType) {
            TYPE_1 -> ViewHolder(PersonCardviewBinding.inflate(inflater, parent, false))
            TYPE_2 -> SecondViewHolder(SampleItemBinding.inflate(inflater, parent, false))
            else -> throw RuntimeException("1")
        }
    }

    private fun bindTypeOne(holder: ViewHolder, position: Int)
    {
        val person = items[position]

        holder.binding.name.text = person.name
        holder.binding.role.text = person.role
        Glide.with(holder.itemView.context).load(person.photoUrl).into(holder.binding.photo)

        holder.itemView.setOnClickListener {
            PersonActivity.show(context, person)
        }
    }

    private fun bindTypeSec(holder: SecondViewHolder, position: Int)
    {
        holder.binding.textt.text = position.toString()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> bindTypeOne(holder, position)
            is SecondViewHolder -> bindTypeSec(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0) {
            return TYPE_1
        }
        return TYPE_2
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val binding: PersonCardviewBinding) : RecyclerView.ViewHolder(binding.root)
    class SecondViewHolder(val binding: SampleItemBinding) : RecyclerView.ViewHolder(binding.root)
}