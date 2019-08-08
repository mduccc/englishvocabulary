package com.indieteam.englishvocabulary.business.provider

import android.util.Log
import com.indieteam.englishvocabulary.model.TranslateModel
import com.indieteam.englishvocabulary.view.App
import com.indieteam.englishvocabulary.viewmodel.TranslateViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TranslateProvider {

    @Inject
    constructor() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var retrofitProvider: RetrofitProvider
    @Inject
    lateinit var firebaseTranslatorProvider: FirebaseTranslatorProvider

    lateinit var call: Call<TranslateModel.Success>
    private fun isCallInitialized() = ::call.isInitialized
    private val cache = HashMap<String, String>()

    fun callCancel() {
        if(isCallInitialized())
            call.cancel()
    }

    inner class Builder {

        fun onlineTranslate(translateViewModel: TranslateViewModel, text: String, format: String, lang: String, key: String) {
            if (cache.containsKey(text)) {
                translateViewModel.setResultText(cache[text]!!)
                translateViewModel.setButtonText("Translate now")
                translateViewModel.setTranslated(true)
            } else {
               callCancel()

                call = retrofitProvider.builder().translate(text, format, lang, key)

                call.enqueue(object : Callback<TranslateModel.Success> {
                    override fun onFailure(call: Call<TranslateModel.Success>, t: Throwable) {
                        Log.d("Throwable", t.toString())
                    }

                    override fun onResponse(
                        call: Call<TranslateModel.Success>,
                        response: Response<TranslateModel.Success>
                    ) {
                        response.body()?.let {
                            val json = it
                            if (json.code == 200) {
                                Log.d("Json", json.toString())

                                var translated = ""
                                for (t in json.text)
                                    translated += t + "\n"

                                if (!cache.containsKey(text))
                                    cache[text] = translated

                                translateViewModel.setResultText(cache[text]!!)
                                translateViewModel.setButtonText("Translate now")
                                translateViewModel.setTranslated(true)
                            } else {
                                Log.d("Json", "Code: " + json.code.toString())
                            }
                        } ?: run {
                            Log.d("Json", "Empty")
                        }
                    }

                })
            }
        }

        fun offlineTranslate(translateViewModel: TranslateViewModel, text: String) {
            firebaseTranslatorProvider.translator.translate(text)
                .addOnSuccessListener {
                    translateViewModel.setResultText(it)
                    translateViewModel.setButtonText("Translate now")
                    translateViewModel.setTranslated(true)
                }.addOnFailureListener {
                    translateViewModel.setResultText("")
                    translateViewModel.setButtonText("Translate now")
                    translateViewModel.setTranslated(true)
                }
        }
    }
}