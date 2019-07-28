package com.indieteam.englishvocabulary.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.android.databinding.library.baseAdapters.BR
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.business.provider.RetrofitProvider
import com.indieteam.englishvocabulary.business.provider.SuggestProvider
import com.indieteam.englishvocabulary.business.provider.TranslateProvider
import com.indieteam.englishvocabulary.business.provider.UrlProvider
import com.indieteam.englishvocabulary.model.TranslateModel
import javax.inject.Inject

class TranslateViewModel : BaseObservable() {
    private val translateView = TranslateModel.TranslateView("", "")
    private val buttonText = TranslateModel.ButtonText("Translate now")

    @Inject
    lateinit var suggestProvider: SuggestProvider
    @Inject
    lateinit var translateProvider: TranslateProvider
    @Inject
    lateinit var translateBuilder: TranslateProvider.Builder

    @Bindable
    val useAnimate = true
    @Bindable
    val positionCursorAlwaysEnd = true
    @Bindable
    val useSuggest = true

    private fun isValid(): Boolean {
        return translateView.inputText.isNotEmpty() && translateView.inputText.isNotEmpty()
    }

    fun setInputText(inputText: String) {
        if (translateProvider.isCallInitialized())
            translateProvider.call.cancel()
        val inputClear = inputText.replace(Regex("[^a-zA-Z]"), "")
        if (inputClear != getInputText())
            setResultText("")
        translateView.inputText = inputClear

        notifyPropertyChanged(BR.inputText)
    }

    fun setResultText(resultText: String) {
        translateView.resultText = resultText
        notifyPropertyChanged(BR.resultText)
    }

    fun setButtonText(buttonText: String) {
        this.buttonText.text = buttonText
        notifyPropertyChanged(BR.buttonText)
    }

    @Bindable
    fun getInputText(): String {
        return translateView.inputText
    }

    @Bindable
    fun getResultText(): String {
        return translateView.resultText
    }

    @Bindable
    fun getButtonText(): String {
        return buttonText.text
    }

    fun onCLick() {
        if (isValid()) {
            Log.d("InputText", "Is valid")
            setButtonText("TRANSLATING...")
            translateBuilder.translate(this, getInputText(), UrlProvider.format, UrlProvider.lang, UrlProvider.key)
        } else {
            Log.d("InputText", "Is not valid")
            setResultText("")
        }
    }

    companion object {
        @BindingAdapter("useEffect")
        @JvmStatic

        fun effect(button: View, useEffect: Boolean) {
            if (useEffect) {
                button.setOnTouchListener { _, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            button.setBackgroundColor(button.resources.getColor(R.color.colorYellowDark2))
                        }
                        MotionEvent.ACTION_UP -> {
                            button.setBackgroundColor(button.resources.getColor(R.color.colorYellow))
                        }
                        else -> {
                        }
                    }
                    false
                }
            }
        }

        @BindingAdapter("positionCursorAlwaysEnd")
        @JvmStatic
        fun setPosition(editText: View, inputText: String) {
            editText as EditText
            if (inputText.isNotEmpty())
                editText.setSelection(inputText.length)
        }
    }

}