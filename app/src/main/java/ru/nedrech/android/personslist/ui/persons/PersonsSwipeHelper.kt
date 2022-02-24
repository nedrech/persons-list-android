package ru.nedrech.android.personslist.ui.persons

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class PersonsSwipeHelper(private val viewModel: PersonsViewModel,
                         private val adapter: PersonsAdapter)
    : ItemTouchHelper.SimpleCallback(ItemTouchHelper.ACTION_STATE_DRAG,
ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.LEFT) {
            viewModel.delete(adapter.items[viewHolder.adapterPosition])
            adapter.deleteItem(viewHolder.adapterPosition)
        } else {
            // TODO: Add edit fragment
        }
    }


}