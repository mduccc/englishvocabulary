package com.indieteam.englishvocabulary.business.provider

import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions


class FirebaseTranslatorProvider {

    private val options = FirebaseTranslatorOptions.Builder()
        .setSourceLanguage(FirebaseTranslateLanguage.EN)
        .setTargetLanguage(FirebaseTranslateLanguage.VI)
        .build()

    val translator = FirebaseNaturalLanguage.getInstance().getTranslator(options)
}