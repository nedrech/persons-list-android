package ru.nedrech.android.personslist.ui.persons

import android.graphics.Canvas
import android.graphics.Rect
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.nedrech.android.personslist.R
import ru.nedrech.android.personslist.data.models.Person
import ru.nedrech.android.personslist.data.models.PersonData
import ru.nedrech.android.personslist.ui.editdialog.EditDialogFragment
import ru.nedrech.android.personslist.ui.editdialog.EditDialogFragment.OnSaveListener

class PersonsSwipeHelper(private val fragment: PersonsFragment) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.ACTION_STATE_IDLE,
    ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
) {

    override fun isLongPressDragEnabled(): Boolean = false

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val item = fragment.adapter.items[position]

        if (direction == ItemTouchHelper.LEFT) {
            fragment.adapter.deleteItem(position)

            showSnackBar(position, item)
        } else {
            EditDialogFragment.show(fragment.parentFragmentManager, item, object : OnSaveListener {
                override fun onSave(updatedData: PersonData) {
                    if (item != updatedData) {
                        item.applyData(updatedData)
                        fragment.adapter.updateItem(position)
                        fragment.viewModel.update(item)
                    }
                }
            })

            fragment.adapter.updateItem(position)
        }
    }

    private fun showSnackBar(position: Int, item: Person) =
        Snackbar
            .make(fragment.binding.root, R.string.removed_from_list, Snackbar.LENGTH_LONG)
            .setAction(R.string.cancel) { fragment.adapter.addItem(position, item) }
            .setActionTextColor(
                ContextCompat.getColor(
                    fragment.requireContext(),
                    R.color.light_blue
                )
            )
            .addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    if (event != Snackbar.Callback.DISMISS_EVENT_ACTION)
                        fragment.viewModel.delete(item)
                }
            })
            .show()

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        var newDx = dX

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val itemView = viewHolder.itemView
            val height = itemView.bottom - itemView.top
            val width = height / 3

            val resId: Int

            val rect = Rect()
            rect.top = itemView.top + width
            rect.bottom = itemView.bottom - width

            if (dX < 0) {
                setST(leftST)

                resId = R.drawable.ic_delete
                rect.left = itemView.right - 2 * width
                rect.right = itemView.right - width
            } else {
                setST(rightST)

                resId = R.drawable.ic_edit
                rect.left = itemView.left + width
                rect.right = itemView.left + 2 * width

                val limiter = itemView.width * rightST
                if (dX >= limiter)
                    newDx = limiter
            }

            ContextCompat.getDrawable(fragment.requireContext(), resId)
                ?.apply { bounds = rect }
                ?.draw(c)
        }
        super.onChildDraw(c, recyclerView, viewHolder, newDx, dY, actionState, isCurrentlyActive)
    }

    private val leftST = .5f
    private val rightST = .2f

    private var defaultST = leftST

    private fun setST(st: Float) {
        if (defaultST != st)
            defaultST = st
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float =
        defaultST
}