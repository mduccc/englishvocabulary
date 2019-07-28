package com.indieteam.englishvocabulary.model

class TranslateModel {
    data class TranslateView(var inputText: String, var resultText: String)
    data class ButtonText(var text: String)
    data class Success (val code: Int, val lang: String, val text: ArrayList<String>)
    data class Fail (val code: Int, val message: String)
}