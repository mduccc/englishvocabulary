package com.indieteam.englishvocabulary.viewmodel

import android.content.Intent
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import com.indieteam.englishvocabulary.business.provider.FirebaseDatabaseManager
import com.indieteam.englishvocabulary.model.FavouriteModel
import com.indieteam.englishvocabulary.view.App
import com.indieteam.englishvocabulary.view.SettingsActivity
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
    @Inject
    lateinit var firebaseDatabaseManager: FirebaseDatabaseManager

    private val favouriteData = ArrayList<FavouriteModel.Item>()
    @Bindable
    val goSettings = true

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
        val delete = databaseManager.deleteVocabularyByName(favouriteData[index].vocabulary)
        firebaseDatabaseManager.deleteFavouriteByVocebulary(this.favouriteData[index].vocabulary)
        if (delete)
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

        @BindingAdapter("goSettings")
        @JvmStatic
        fun goSettings(view: View, boolean: Boolean) {
            if (boolean) {
                view.setOnClickListener {
                    val intent = Intent(view.context, SettingsActivity::class.java)
                    view.context.startActivity(intent)
                }
            }
        }
    }
}