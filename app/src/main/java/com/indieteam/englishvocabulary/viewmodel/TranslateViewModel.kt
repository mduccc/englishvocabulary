package com.indieteam.englishvocabulary.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.indieteam.englishvocabulary.BR
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import com.indieteam.englishvocabulary.business.provider.SuggestProvider
import com.indieteam.englishvocabulary.business.provider.TranslateProvider
import com.indieteam.englishvocabulary.model.TranslateModel
import com.indieteam.englishvocabulary.view.App
import javax.inject.Inject


class TranslateViewModel : BaseObservable {

    @Inject
    constructor() {
        App.appComponent.inject(this)
    }

    private val translateView = TranslateModel.TranslateView("", "")
    private val buttonText = TranslateModel.ButtonText("Translate now")

    @Inject
    lateinit var suggestProvider: SuggestProvider
    @Inject
    lateinit var translateProvider: TranslateProvider
    @Inject
    lateinit var databaseManager: DatabaseManager

    @Bindable
    val useAnimate = true
    @Bindable
    val positionCursorAlwaysEnd = true
    @Bindable
    val useSuggest = true

    private var buttonClearInputState = false
    private var translated = false
    private var favoriteState = false
    private var favoriteDrawable = R.drawable.ic_star_border

    private fun isValid(): Boolean {
        return translateView.inputText.isNotEmpty() && translateView.inputText.isNotEmpty()
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

    @Bindable
    fun getFavoriteDrawable(): Int {
        return favoriteDrawable
    }

    @Bindable
    fun getTranslated(): Boolean {
        return translated
    }

    @Bindable
    fun getButtonClearInputState(): Boolean {
        return buttonClearInputState
    }


    fun setInputText(inputText: String) {
        translateProvider.callCancel()
        val inputClear = inputText.replace(Regex("[^a-zA-Z]"), "")
        if (inputClear != getInputText()) {
            setResultText("")
            setTranslated(false)
            favoriteDrawable = R.drawable.ic_star_border
            setFavoriteDrawable(favoriteDrawable)
        }
        translateView.inputText = inputClear
        setButtonText("Translate now")

        setButtonClearInputState(translateView.inputText.isNotEmpty())

        notifyPropertyChanged(BR.inputText)
    }

    fun setResultText(resultText: String) {
        translateView.resultText = resultText.replace("\n", "")
        notifyPropertyChanged(BR.resultText)
    }

    fun setButtonText(buttonText: String) {
        this.buttonText.text = buttonText
        notifyPropertyChanged(BR.buttonText)
    }

    fun setFavoriteDrawable(drawable: Int) {
        this.favoriteDrawable = drawable
        notifyPropertyChanged(BR.favoriteDrawable)
    }

    fun setTranslated(boolean: Boolean) {
        this.translated = boolean
        favoriteState = false

        if (boolean && databaseManager.isVocabularyExists(getInputText())) {
            favoriteState = true
            favoriteDrawable = R.drawable.ic_star_fit
            setFavoriteDrawable(favoriteDrawable)
        }
        notifyPropertyChanged(BR.translated)
    }

    fun setButtonClearInputState(boolean: Boolean) {
        buttonClearInputState = boolean
        notifyPropertyChanged(BR.buttonClearInputState)
    }

    fun translateOnCLick() {
        if (isValid()) {
            setTranslated(false)
            Log.d("InputText", "Is valid")
            setButtonText("TRANSLATING...")
            translateProvider.Builder().offlineTranslate(this, getInputText())
            //translateProvider.Builder().onlineTranslate(this, getInputText(), UrlProvider.Yandex.format, UrlProvider.Yandex.lang, UrlProvider.Yandex.key)
        } else {
            Log.d("InputText", "Is not valid")
            setResultText("")
        }
    }

    fun favoriteOnClick() {
        favoriteState = !favoriteState
        Log.d("favoriteState", favoriteState.toString())

        if (favoriteState) {
            favoriteDrawable = R.drawable.ic_star_fit
            if (getResultText().isNotEmpty() && getResultText().isNotBlank()) {
                val insert = databaseManager.insertVocabulary(getInputText(), getResultText(), null)
                Log.d("insertVocabulary vocabulary", insert.toString())
            }
        } else {
            favoriteDrawable = R.drawable.ic_star_border
            val delete = databaseManager.deleteVocabularyByName(getInputText())
            Log.d("deleteVocabularyByName vocabulary", delete.toString())
        }

        setFavoriteDrawable(favoriteDrawable)

    }

    fun clearInputClick() {
        setInputText("")
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
        fun setPosition(editText: EditText, inputText: String) {
            if (inputText.isNotEmpty())
                editText.setSelection(inputText.length)
        }

        @BindingAdapter("android:src")
        @JvmStatic
        fun setImageDrawable(imageView: ImageView, drawable: Int) {
            imageView.setImageDrawable(imageView.resources.getDrawable(drawable))
        }
    }

}