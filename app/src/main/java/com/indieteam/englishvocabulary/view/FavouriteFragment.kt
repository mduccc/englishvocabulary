package com.indieteam.englishvocabulary.view

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import com.indieteam.englishvocabulary.business.provider.FirebaseDatabaseManager
import com.indieteam.englishvocabulary.databinding.FragmentFavouriteBindingImpl
import com.indieteam.englishvocabulary.model.FavouriteModel
import com.indieteam.englishvocabulary.view.adapter.FavouriteAdapter
import com.indieteam.englishvocabulary.viewmodel.FavouriteViewModel
import kotlinx.android.synthetic.main.fragment_favourite.*
import kotlinx.android.synthetic.main.fragment_favourite.view.*
import javax.inject.Inject

class FavouriteFragment : Fragment, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    constructor() {
        App.appComponent.inject(this)
    }

    override fun onRefresh() {
        Log.d("onRefresh", "onRefresh")
        databaseManager.getAccID()?.let {
            Log.d("Account from DB", it)
            firebaseDatabaseManager.getVocabularys()
        } ?: run {
            Log.d("Account from DB", "null")
            onRefreshed()
        }
    }

    fun onRefreshed() {
        Log.d("onRefresh", "onRefreshed")
        data.clear()
        data.addAll(favouriteViewModel.databaseManager.getFavorites())
        favouriteViewModel.setFavouriteData(data)
        try {
            swipe_refresh_layout.isRefreshing = false
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setRefresh() {
        Log.d("onRefresh", "setRefresh")
        try {
            swipe_refresh_layout.isRefreshing = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Inject
    lateinit var favouriteViewModel: FavouriteViewModel
    @Inject
    lateinit var favouriteAdapter: FavouriteAdapter
    @Inject
    lateinit var databaseManager: DatabaseManager
    @Inject
    lateinit var firebaseDatabaseManager: FirebaseDatabaseManager

    private var deleteButtonVisible = false
    private var posSwiped = -1
    private var lastSwipe = -1
    private var moving = false
    private val data = ArrayList<FavouriteModel.Item>()

    private val swipeController = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START) {
        override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
            moving = true
            return false
        }

        override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
            moving = false
            Log.d("Moving", "$moving")
            val position = p0.layoutPosition

            // Close Item swiped before
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

            // dX of Item run from 0 to `-X` width of screen

            if (dX <= -deleteButtonLeft) {
                deleteButtonVisible = true
                moving = false
            } else {
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
                Log.d("X Item end", "${item.x + item.width}")
                Log.d("Y click", event.y.toString())
                Log.d("Y Item start", item.y.toString())
                Log.d("Y Item end", "${item.y + item.height}")
                Log.d("Button Visible", deleteButtonVisible.toString())

                if (event.action == MotionEvent.ACTION_UP && event.y > item.y && event.y < item.y + item.height
                    && event.x > item.x + item.width && !moving
                ) {
                    if (deleteButtonVisible) {
                        favouriteViewModel.removeFavouruteData(posSwiped)
                        favouriteAdapter.removeData(posSwiped)

                        data.clear()
                        data.addAll(favouriteViewModel.databaseManager.getFavorites())
                        deleteButtonVisible = false

//                        Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT)
//                            .show()
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
        (requireActivity().application as App).apply {
        }

        val binding = DataBindingUtil.inflate<FragmentFavouriteBindingImpl>(
            inflater,
            R.layout.fragment_favourite,
            container,
            false
        )
        binding.favouriteViewModel = favouriteViewModel
        binding.executePendingBindings()
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data.addAll(favouriteViewModel.databaseManager.getFavorites())
        favouriteViewModel.setFavouriteData(data)

        recycler_view.adapter = favouriteAdapter
        recycler_view.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
        ItemTouchHelper(swipeController).attachToRecyclerView(view.recycler_view)
        search_favorite.onActionViewExpanded()
        search_favorite.isFocusable = false
        search_favorite.clearFocus()

        search_favorite.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.d("Submit", p0)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d("Searching", p0)
                p0?.let { it ->
                    if (it.isEmpty()) {
                        favouriteViewModel.setFavouriteData(data)
                    } else {
                        val searchData = data.filter { it.vocabulary.toLowerCase().contains(p0.toLowerCase()) }
                            .toCollection(ArrayList())
                        favouriteViewModel.setFavouriteData(searchData)
                    }
                }
                return true
            }

        })

        swipe_refresh_layout.setOnRefreshListener(this)
    }
}
