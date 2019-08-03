package com.indieteam.englishvocabulary.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.indieteam.englishvocabulary.BR
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import com.indieteam.englishvocabulary.model.FavouriteModel
import javax.inject.Inject
import kotlin.collections.ArrayList
import com.indieteam.englishvocabulary.view.adapter.FavouriteAdapter as FavouriteAdapter1

class FavouriteViewModel: BaseObservable() {

    @Inject
    lateinit var databaseManager: DatabaseManager

    private val favouriteData = ArrayList<FavouriteModel.item>()

    fun updateFavouriteData(favouriteData: ArrayList<FavouriteModel.item>) {
        this.favouriteData.addAll(favouriteData)
        notifyPropertyChanged(BR.favouriteData)
    }

    fun setFavouriteData(favouriteData: ArrayList<FavouriteModel.item>) {
        this.favouriteData.clear()
        this.favouriteData.addAll(favouriteData)
        notifyPropertyChanged(BR.favouriteData)
    }

    @Bindable
    fun getFavouriteData():ArrayList<FavouriteModel.item> {
        return favouriteData
    }

    companion object{
        @BindingAdapter("data")
        @JvmStatic
        fun setData(recyclerView: RecyclerView, data: ArrayList<FavouriteModel.item>) {
            if (recyclerView.adapter is com.indieteam.englishvocabulary.view.adapter.FavouriteAdapter) {
                Log.d("recyclerView.adapter", "Not null")
                (recyclerView.adapter as com.indieteam.englishvocabulary.view.adapter.FavouriteAdapter).apply {
                    setData(data)
                }
            } else {
                Log.d("recyclerView.adapter", "Null")
            }
        }
    }


}