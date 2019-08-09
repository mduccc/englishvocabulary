package com.indieteam.englishvocabulary.business.provider

import android.util.Log
import com.indieteam.englishvocabulary.view.App
import com.indieteam.englishvocabulary.view.update.OnDownloadModel
import javax.inject.Inject

class TranslateModelProvider {

    @Inject
    constructor() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var firebaseTranslatorProvider: FirebaseTranslatorProvider

    fun download(onDownloadModel: OnDownloadModel) {
        onDownloadModel.onDownload()
        firebaseTranslatorProvider.translator.downloadModelIfNeeded()
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