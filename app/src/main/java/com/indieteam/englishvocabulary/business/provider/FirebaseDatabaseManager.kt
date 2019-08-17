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

    companion object {
    }

    @Inject
    constructor() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var firebaseDatabaseProvider: FirebaseDatabaseProvider


    fun insertAccount(accountModel: AccountModel) {
        firebaseDatabaseProvider.accountsRef
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    var exists = false
                    for (child in p0.children) {
                        val data = child.getValue(AccountModel::class.java)
                        if (accountModel.email == data?.email) {
                            exists = true
                            Log.d(
                                "Account Info",
                                "${data?.accID} ${data?.email} ${data?.type} ${data?.description}"
                            )
                        }
                    }

                    Log.d("Account Exists", exists.toString())
                    if (!exists)
                        firebaseDatabaseProvider.accountsRef.push().setValue(accountModel)
                }
            })
    }

    fun getVocabularys(email: String) {
        firebaseDatabaseProvider.accountsRef
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    var exists = false
                    var accId: String? = null
                    for (child in p0.children) {
                        val data = child.getValue(AccountModel::class.java)
                        if (email == data?.email) {
                            exists = true
                            accId = data?.accID
                            Log.d(
                                "Account Info",
                                "${data?.accID} ${data?.email} ${data?.type} ${data?.description}"
                            )
                        }
                    }

                    Log.d("Account Exists", exists.toString())
                    if (exists && accId != null) {
                        firebaseDatabaseProvider.favouritesRef
                            .addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onCancelled(p0: DatabaseError) {

                                }

                                override fun onDataChange(p0: DataSnapshot) {
                                    for (child in p0.children) {
                                        val data = child.getValue(FavouriteModel.Item::class.java)
                                        Log.d(
                                            "Favourites Info",
                                            "${data?.accID} ${data?.vocabulary} ${data?.vi} ${data?.description}"
                                        )
                                        if (data?.accID == accId) {

                                        }
                                    }
                                }
                            })
                    }

                }
            })

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