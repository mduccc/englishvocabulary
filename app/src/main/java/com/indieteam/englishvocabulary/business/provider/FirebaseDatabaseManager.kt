package com.indieteam.englishvocabulary.business.provider

import android.content.Context
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.indieteam.englishvocabulary.model.AccountModel
import com.indieteam.englishvocabulary.model.FavouriteModel
import com.indieteam.englishvocabulary.view.App
import com.indieteam.englishvocabulary.view.MainActivity
import com.indieteam.englishvocabulary.view.MainActivity_MembersInjector
import java.lang.Exception
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
    @Inject
    lateinit var databaseManager: DatabaseManager
    @Inject
    lateinit var mainActivity: MainActivity

    fun insertAccount(accountModel: AccountModel) {
        firebaseDatabaseProvider.accountsRef
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    p0.toException().printStackTrace().toString()
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var exists = false
                    for (child in p0.children) {
                        val data = child.getValue(AccountModel::class.java)
                        if (accountModel.email == data?.email) {
                            exists = true
                            val newAccountModel = AccountModel(data.email, data.accID, data.type, data.description)
                            databaseManager.insertAccount(newAccountModel)
                            getVocabularys()
                            Log.d(
                                "Account Info",
                                "${data?.accID} ${data?.email} ${data?.type} ${data?.description}"
                            )
                        }
                    }
                    Log.d("Account Exists", exists.toString())
                    if (!exists) {
                        firebaseDatabaseProvider.accountsRef.push().setValue(accountModel)
                        databaseManager.insertAccount(accountModel)
                        getVocabularys()
                    }
                }
            })
    }

    fun getVocabularys() {
        firebaseDatabaseProvider.accountsRef
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    //favouriteFragment.onRefreshed()
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var exists = false
                    val accId = databaseManager.getAccID()
                    val email = databaseManager.getEmail()
                    if (email != null && accId != null) {
                        for (child in p0.children) {
                            val data = child.getValue(AccountModel::class.java)
                            if (email == data?.email) {
                                exists = true
                                Log.d(
                                    "Account Info",
                                    "${data?.accID} ${data?.email} ${data?.type} ${data?.description}"
                                )
                            }
                        }

                        Log.d("Account Exists", exists.toString())
                        if (exists) {
                            firebaseDatabaseProvider.favouritesRef
                                .addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onCancelled(p0: DatabaseError) {

                                    }

                                    override fun onDataChange(p0: DataSnapshot) {
                                        val favourite = ArrayList<FavouriteModel.Item>()
                                        for (child in p0.children) {
                                            val data = child.getValue(FavouriteModel.Item::class.java)
                                            if (data?.accID == accId) {
                                                favourite.add(data)
                                            }
                                        }
                                        if (favourite.isNotEmpty()) {
                                            databaseManager.deleteAllVocabulary()
                                            for (data in favourite) {
                                                Log.d(
                                                    "Favourites Info",
                                                    "${data?.accID} ${data?.vocabulary} ${data?.vi} ${data?.description}"
                                                )
                                                databaseManager.insertVocabulary(data)
                                            }
                                        } else {
                                            Log.d(
                                                "Favourites Info",
                                                "Empty"
                                            )
                                            databaseManager.deleteAllVocabulary()
                                        }
                                    }
                                })
                        }
                    }
                    mainActivity.favouriteFragment.onRefreshed()
                }
            })

    }

    fun insertFavourite(favouriteModel: FavouriteModel.Item) {
        firebaseDatabaseProvider.accountsRef
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    var exists = false
                    val accId = databaseManager.getAccID()
                    val email = databaseManager.getEmail()
                    if (email != null && accId != null) {
                        for (child in p0.children) {
                            val data = child.getValue(AccountModel::class.java)
                            if (email == data?.email) {
                                exists = true
                                Log.d(
                                    "Account Info",
                                    "${data?.accID} ${data?.email} ${data?.type} ${data?.description}"
                                )
                            }
                        }

                        Log.d("Account Exists", exists.toString())
                        if (exists) {
                            firebaseDatabaseProvider.favouritesRef
                                .addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onCancelled(p0: DatabaseError) {

                                    }

                                    override fun onDataChange(p0: DataSnapshot) {
                                        var vocabularyExists = false
                                        for (child in p0.children) {
                                            val data = child.getValue(FavouriteModel.Item::class.java)
                                            if (data?.accID == accId && data?.vocabulary == favouriteModel.vocabulary)
                                                vocabularyExists = true
                                        }

                                        if (!vocabularyExists) {
                                            val newFavouriteModel = FavouriteModel.Item(
                                                null,
                                                databaseManager.getAccID(),
                                                favouriteModel.vocabulary,
                                                favouriteModel.vi,
                                                ""
                                            )
                                            firebaseDatabaseProvider.favouritesRef.push()
                                                .setValue(newFavouriteModel)
                                        }

                                    }
                                })
                        }
                    }
                }
            })
    }

    fun deleteFavouriteByVocebulary(vocabulary: String) {
        firebaseDatabaseProvider.accountsRef
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    var exists = false
                    val accId = databaseManager.getAccID()
                    val email = databaseManager.getEmail()
                    if (email != null && accId != null) {
                        for (child in p0.children) {
                            val data = child.getValue(AccountModel::class.java)
                            if (email == data?.email) {
                                exists = true
                                Log.d(
                                    "Account Info",
                                    "${data?.accID} ${data?.email} ${data?.type} ${data?.description}"
                                )
                            }
                        }

                        Log.d("Account Exists", exists.toString())
                        if (exists) {
                            firebaseDatabaseProvider.favouritesRef
                                .addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onCancelled(p0: DatabaseError) {

                                    }

                                    override fun onDataChange(p0: DataSnapshot) {
                                        for (child in p0.children) {
                                            val data = child.getValue(FavouriteModel.Item::class.java)
                                            if (data?.accID == accId && data?.vocabulary == vocabulary) {
                                                Log.d("Will delete", vocabulary)
                                                child.ref.removeValue()
                                            }
                                        }

                                    }
                                })
                        }
                    }
                }
            })
    }
}