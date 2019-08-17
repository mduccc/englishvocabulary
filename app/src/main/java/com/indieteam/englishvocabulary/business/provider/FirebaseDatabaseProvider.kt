package com.indieteam.englishvocabulary.business.provider

import com.google.firebase.database.FirebaseDatabase

class FirebaseDatabaseProvider {

   private val database = FirebaseDatabase.getInstance()
    val accountsRef = database.getReference("accounts")
    val favouritesRef = database.getReference("favourites")
}