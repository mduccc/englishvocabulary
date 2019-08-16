package com.indieteam.englishvocabulary.business.provider

import com.indieteam.englishvocabulary.model.FavouriteModel
import com.indieteam.englishvocabulary.view.App
import javax.inject.Inject

class FirebaseDatabaseManager {

    @Inject
    constructor() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var firebaseDatabaseProvider: FirebaseDatabaseProvider

    fun validAccount(account: String): Boolean {
        val result = false

        return result
    }

    fun addAccount(account: String): Boolean {
        val result = false

        return result
    }

    fun getVocabularys(account: String): ArrayList<FavouriteModel.Item> {
        val result = ArrayList<FavouriteModel.Item>()

        return result
    }

    fun addFavourite(account: String): Boolean {
        val result = false

        return result
    }

    fun deleteFavouriteByVocebulary(vocabulary: String, account: String): Boolean {
        val result = false

        return result
    }
}