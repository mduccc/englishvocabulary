package com.indieteam.englishvocabulary.business.provider

import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions


class FirebaseTranslateProvider {

    private val options = FirebaseTranslatorOptions.Builder()
        .setSourceLanguage(FirebaseTranslateLanguage.EN)
        .setTargetLanguage(FirebaseTranslateLanguage.VI)
        .build()

    val translator: FirebaseTranslator = FirebaseNaturalLanguage.getInstance().getTranslator(options)
}