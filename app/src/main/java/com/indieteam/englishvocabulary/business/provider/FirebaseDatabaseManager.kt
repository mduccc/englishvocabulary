package com.indieteam.englishvocabulary.business.provider

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.indieteam.englishvocabulary.model.AccountModel
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

    fun validAccount(account: String) {
        
    }

    fun getAccount(): String {
        var result = ""

        firebaseDatabaseProvider.accountsRef
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    val account = p0.getValue(AccountModel::class.java)
                    Log.d("Account Info", account.toString())
                }
            })

        return result
    }

    fun insertAccount(accountModel: AccountModel) {
        firebaseDatabaseProvider.accountsRef.push().setValue(accountModel)
    }

    fun getVocabularys(account: String): ArrayList<FavouriteModel.Item> {
        val result = ArrayList<FavouriteModel.Item>()

        return result
    }

    fun insertFavourite(account: String): Boolean {
        val result = false

        return result
    }

    fun deleteFavouriteByVocebulary(vocabulary: String, account: String): Boolean {
        val result = false

        return result
    }
}