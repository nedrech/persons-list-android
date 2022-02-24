package ru.nedrech.android.personslist.ui.persons

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.nedrech.android.personslist.R
import ru.nedrech.android.personslist.data.models.Person


class PersonsSwipeHelper(private val fragment: PersonsFragment)
    : ItemTouchHelper.SimpleCallback(0,
ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {

    override fun isLongPressDragEnabled(): Boolean = false

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.LEFT) {
            val position = viewHolder.adapterPosition
            val item = fragment.adapter.items[position]

            fragment.adapter.deleteItem(position)

            showSnackBar(position, item)
        } else {
            // TODO: Add edit fragment
        }
    }

    private fun showSnackBar(position: Int, item: Person) =
        Snackbar
            .make(fragment.binding.root, R.string.removed_from_list, Snackbar.LENGTH_LONG)
            .setAction(R.string.cancel) { fragment.adapter.addItem(position, item) }
            .setActionTextColor(ContextCompat.getColor(fragment.requireContext(), R.color.light_blue))
            .addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    if (event != Snackbar.Callback.DISMISS_EVENT_ACTION)
                        fragment.viewModel.delete(item)
                }
            })
            .show()
}