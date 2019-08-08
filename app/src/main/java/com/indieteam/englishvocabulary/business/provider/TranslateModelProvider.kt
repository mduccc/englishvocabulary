package com.indieteam.englishvocabulary.business.provider

import android.util.Log
import com.indieteam.englishvocabulary.view.App
import javax.inject.Inject

class TranslateModelProvider {

    @Inject
    constructor() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var firebaseTranslatorProvider: FirebaseTranslatorProvider

    fun download() {
        firebaseTranslatorProvider.translator.downloadModelIfNeeded()
            .addOnSuccessListener {
                Log.d("Translate Model", "Downloaded")
            }
            .addOnFailureListener { exception ->
                exception.printStackTrace()
            }
    }
}