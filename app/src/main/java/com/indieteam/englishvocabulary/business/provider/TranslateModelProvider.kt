package com.indieteam.englishvocabulary.business.provider

import android.util.Log
import com.indieteam.englishvocabulary.view.App
import com.indieteam.englishvocabulary.view.update.OnDownloadModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TranslateModelProvider {

    @Inject
    constructor() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var firebaseTranslateProvider: FirebaseTranslateProvider

    fun download(onDownloadModel: OnDownloadModel) {
        onDownloadModel.onDownload()
        firebaseTranslateProvider.translator.downloadModelIfNeeded()
            .addOnSuccessListener {
                Log.d("Translate Model", "Downloaded")
                onDownloadModel.onSuccess()
            }
            .addOnFailureListener { exception ->
                exception.printStackTrace()
                onDownloadModel.onFailure()
            }

    }
}