package com.indieteam.englishvocabulary.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.indieteam.englishvocabulary.BR
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import com.indieteam.englishvocabulary.model.FavouriteModel
import com.indieteam.englishvocabulary.view.App
import javax.inject.Inject
import kotlin.collections.ArrayList
import com.indieteam.englishvocabulary.view.adapter.FavouriteAdapter as FavouriteAdapter1

class FavouriteViewModel: BaseObservable {

    @Inject
    constructor(){
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var databaseManager: DatabaseManager

    private val favouriteData = ArrayList<FavouriteModel.Item>()

    @Bindable
    fun getFavouriteData():ArrayList<FavouriteModel.Item> {
        return favouriteData
    }

    fun updateFavouriteData(favouriteData: ArrayList<FavouriteModel.Item>) {
        this.favouriteData.addAll(favouriteData)
        notifyPropertyChanged(BR.favouriteData)
    }

    fun setFavouriteData(favouriteData: ArrayList<FavouriteModel.Item>) {
        this.favouriteData.clear()
        this.favouriteData.addAll(favouriteData)
        notifyPropertyChanged(BR.favouriteData)
    }

    fun removeFavouruteData(index: Int) {
        val delete = databaseManager.delete(favouriteData[index].vocabulary)
        this.favouriteData.removeAt(index)
        //notifyPropertyChanged(BR.favouriteData)
    }

    companion object{
        @BindingAdapter("data")
        @JvmStatic
        fun setData(recyclerView: RecyclerView, data: ArrayList<FavouriteModel.Item>) {
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