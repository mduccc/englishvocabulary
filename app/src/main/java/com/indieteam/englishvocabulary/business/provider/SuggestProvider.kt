package com.indieteam.englishvocabulary.business.provider

import android.content.Context
import android.util.Log
import android.view.textservice.*
import java.util.*
import javax.inject.Singleton

@Singleton
class SuggestProvider(private val context: Context): SpellCheckerSession.SpellCheckerSessionListener {

    override fun onGetSentenceSuggestions(results: Array<out SentenceSuggestionsInfo>?) {
        results?.let {
            Log.d("Suggest", "Not Empty")
            for (sentenceSuggestionsInfo in it) {
                for (i in 0 until sentenceSuggestionsInfo.suggestionsCount) {
                    val suggestionsInfo = sentenceSuggestionsInfo.getSuggestionsInfoAt(i)

                    for (j in 0 until suggestionsInfo.suggestionsCount) {
                        Log.d("Suggested", suggestionsInfo.getSuggestionAt(j).toString())
                    }
                }
            }
        } ?: run {
            Log.d("Suggest", "Empty")
        }
    }

    override fun onGetSuggestions(results: Array<out SuggestionsInfo>?) {
        Log.d("Suggest", "onGetSuggestions")
    }

    inner class Builder {

        inner class input(private val input: String) {
            fun suggest() {
                if (input.isNotEmpty() && input.isNotBlank()) {
                    val tsm = context.getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE) as TextServicesManager
                    val session = tsm.newSpellCheckerSession(null, Locale.ENGLISH, this@SuggestProvider, false)
                    session.getSentenceSuggestions(arrayOf(TextInfo(input)), 5)
                }
            }
        }
    }
}