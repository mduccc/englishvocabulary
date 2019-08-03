package com.indieteam.englishvocabulary.view

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.business.component.DaggerFavouruteComponent
import com.indieteam.englishvocabulary.business.module.ContextModule
import com.indieteam.englishvocabulary.business.module.DatabaseModule
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import com.indieteam.englishvocabulary.databinding.FragmentFavouriteBindingImpl
import com.indieteam.englishvocabulary.view.adapter.FavouriteAdapter
import com.indieteam.englishvocabulary.viewmodel.FavouriteViewModel
import kotlinx.android.synthetic.main.fragment_favourite.view.*


class FavouriteFragment : Fragment() {

    private val favouriteViewModel = FavouriteViewModel()
    private val favouriteAdapter = FavouriteAdapter()

    private var deleteButtonVisible = false
    private var posSwiped = -1
    private var lastSwipe = -1
    private var moving = false

    private val swipeController = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START) {
        override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
            moving = true
            return false
        }

        override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
            moving = false
            Log.d("Moving", "$moving")
            val position = p0.layoutPosition

            // Close item swiped before
            if (lastSwipe != -1 && lastSwipe != position)
                favouriteAdapter.notifyItemChanged(lastSwipe)

            lastSwipe = position
            deleteButtonVisible = true
            Log.d("Button Visible", deleteButtonVisible.toString())
        }

        override fun onChildDraw(
            c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
            dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
        ) {

            posSwiped = viewHolder.adapterPosition

            Log.d("dX", dX.toString())
            Log.d("Item postion", posSwiped.toString())
            val view = viewHolder.itemView

            val paint = Paint()
            paint.color = resources.getColor(R.color.colorWhite)
            paint.textSize = 35f
            paint.isAntiAlias = true

            // Fix position for button
            val deleteButtonLeft = view.right - (view.right / 5f)
            val deleteButtonTop = view.top.toFloat()
            val deleteButtonRight = view.right.toFloat() - view.paddingRight
            val deleteButtonBottom = view.bottom.toFloat()

            Log.d("Delete Button Left X", deleteButtonLeft.toString())

            // Draw a button
            val radius = 15f
            val deleteButtonDelete = RectF(deleteButtonLeft, deleteButtonTop, deleteButtonRight, deleteButtonBottom)
            c.drawRoundRect(deleteButtonDelete, radius, radius, paint)

            // Set color for draw text inside button
            paint.color = resources.getColor(R.color.colorAccent)

            // Button text
            val textButton = "Delete"

            // Get witdth, height of button text
            val rect = Rect()
            paint.getTextBounds(textButton, 0, textButton.length, rect)

            c.drawText(
                "Delete",
                deleteButtonDelete.centerX() - rect.width() / 2f,
                deleteButtonDelete.centerY() + rect.height() / 2f,
                paint
            )

            // dX of item run from 0 to `-X` width of screen

            if (dX <= - deleteButtonLeft) {
                deleteButtonVisible = true
                moving = false
            } else
            {
                deleteButtonVisible = false
                moving = true
            }

            if (dX == 0.0f)
                moving = false

            Log.d("Moving", "$moving")

            Log.d("Button Visible", deleteButtonVisible.toString())

            if (deleteButtonVisible)
                clickDeleteButtonListener(recyclerView, posSwiped)

            // Item will stop in dX / 5,
            super.onChildDraw(c, recyclerView, viewHolder, dX / 5f, dY, actionState, isCurrentlyActive)
        }

        // Swipe back (start, end, top, down)
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            return makeMovementFlags(0, ItemTouchHelper.START)
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun clickDeleteButtonListener(recyclerView: RecyclerView, posSwiped: Int) {
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(posSwiped)
        val item = viewHolder?.itemView

        item?.let {
            recyclerView.setOnTouchListener { v, event ->
                Log.d("X click", event.x.toString())
                Log.d("X item end", "${item.x + item.width}")
                Log.d("Y click", event.y.toString())
                Log.d("Y item start", item.y.toString())
                Log.d("Y item end", "${item.y + item.height}")
                Log.d("Button Visible", deleteButtonVisible.toString())

                if(event.action == MotionEvent.ACTION_UP && event.y > item.y && event.y < item.y + item.height
                    && event.x > item.x + item.width && !moving){
                    if (deleteButtonVisible) {
                        Toast.makeText(requireContext(), "Click to Button Delete ($posSwiped)", Toast.LENGTH_SHORT).show()
                        deleteButtonVisible = false
                    }
                }
                false
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val favouriteComponent = DaggerFavouruteComponent.builder()
            .contextModule(ContextModule(requireContext()))
            .databaseModule(DatabaseModule(DatabaseManager(requireContext())))
            .build()

        favouriteComponent.poke(favouriteViewModel)

        val binding = DataBindingUtil.inflate<FragmentFavouriteBindingImpl>(inflater, R.layout.fragment_favourite, container, false)
        binding.favouriteViewModel = favouriteViewModel
        binding.executePendingBindings()
        val view = binding.root

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = favouriteViewModel.databaseManager.getFavorites()
        favouriteViewModel.setFavouriteData(data)

        view.recycler_view.adapter = favouriteAdapter
        view.recycler_view.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        ItemTouchHelper(swipeController).attachToRecyclerView(view.recycler_view)
    }

}
