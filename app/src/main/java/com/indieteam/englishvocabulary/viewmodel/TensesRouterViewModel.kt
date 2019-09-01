package com.indieteam.englishvocabulary.viewmodel

import android.content.Intent
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.indieteam.englishvocabulary.view.TensesActivity

class Label {
    companion object {
        const val simplePresent = "Simple present"
        const val presentContinuous = "Present continuous"
        const val presentPerfect = "Present perfect"
        const val pastSimple = "Past simple"
        const val pastContinuous = "Past continuous"
        const val pastPerfect = "Past perfect"
        const val simpleFuture = "Simple future"
        const val futureContinuous = "Future continuous"
        const val futurePerfect = "Future perfect"
    }
}

class TensesRouterViewModel : BaseObservable() {
    private lateinit var simplePresent: String
    private lateinit var presentContinuous: String
    private lateinit var presentPerfect: String
    private lateinit var pastSimple: String
    private lateinit var pastContinuous: String
    private lateinit var pastPerfect: String
    private lateinit var simpleFuture: String
    private lateinit var futureContinuous: String
    private lateinit var futurePerfect: String

    init {
        setSimplePresent(Label.simplePresent)
        setPresentContinuous(Label.presentContinuous)
        setPresentPerfect(Label.presentPerfect)
        setPastSimple(Label.pastSimple)
        setPastContinuous(Label.pastContinuous)
        setPastPerfect(Label.pastPerfect)
        setSimpleFuture(Label.simpleFuture)
        setFutureContinuous(Label.futureContinuous)
        setFuturePerfect(Label.futurePerfect)
    }

    @Bindable
    fun getSimplePresent(): String {
        return simplePresent
    }

    @Bindable
    fun getPresentContinuous(): String {
        return presentContinuous
    }

    @Bindable
    fun getPresentPerfect(): String {
        return presentPerfect
    }

    @Bindable
    fun getPastSimple(): String {
        return pastSimple
    }

    @Bindable
    fun getPastContinuous(): String {
        return pastContinuous
    }

    @Bindable
    fun getPastPerfect(): String {
        return pastPerfect
    }

    @Bindable
    fun getSimpleFuture(): String {
        return simpleFuture
    }

    @Bindable
    fun getFutureContinuous(): String {
        return futureContinuous
    }

    @Bindable
    fun getFuturePerfect(): String {
        return futurePerfect
    }

    fun setSimplePresent(simplePresent: String) {
        this.simplePresent = simplePresent
        notifyPropertyChanged(BR.simplePresent)
    }

    fun setPresentContinuous(presentContinuous: String) {
        this.presentContinuous = presentContinuous
        notifyPropertyChanged(BR.presentContinuous)
    }

    fun setPresentPerfect(presentPerfect: String) {
        this.presentPerfect = presentPerfect
        notifyPropertyChanged(BR.presentPerfect)
    }

    fun setPastSimple(pastSimple: String) {
        this.pastSimple = pastSimple
        notifyPropertyChanged(BR.pastSimple)
    }

    fun setPastContinuous(pastContinuous: String) {
        this.pastContinuous = pastContinuous
        notifyPropertyChanged(BR.pastContinuous)
    }

    fun setPastPerfect(pastPerfect: String) {
        this.pastPerfect = pastPerfect
        notifyPropertyChanged(BR.pastPerfect)
    }

    fun setSimpleFuture(simpleFuture: String) {
        this.simpleFuture = simpleFuture
        notifyPropertyChanged(BR.simpleFuture)
    }

    fun setFutureContinuous(futureContinuous: String) {
        this.futureContinuous = futureContinuous
        notifyPropertyChanged(BR.futureContinuous)
    }

    fun setFuturePerfect(futurePerfect: String) {
        this.futurePerfect = futurePerfect
        notifyPropertyChanged(BR.futurePerfect)
    }

    companion object {
        @BindingAdapter("direct")
        @JvmStatic
        fun setDirect(view: View, label: String) {
            view.setOnClickListener {
                val title: String
                val intent = Intent(view.context, TensesActivity::class.java)

                when (label) {
                    Label.simplePresent -> {
                        title = Label.simplePresent
                    }
                    Label.presentContinuous -> {
                        title = Label.presentContinuous
                    }

                    Label.presentPerfect -> {
                        title = Label.presentPerfect
                    }

                    Label.pastSimple -> {
                        title = Label.pastSimple
                    }

                    Label.pastContinuous -> {
                        title = Label.pastContinuous
                    }

                    Label.pastPerfect -> {
                        title = Label.pastPerfect
                    }

                    Label.simpleFuture -> {
                        title = Label.simpleFuture
                    }

                    Label.futureContinuous -> {
                        title = Label.futureContinuous
                    }
                    Label.futurePerfect -> {
                        title = Label.futurePerfect
                    }
                    else -> {
                        title = ""
                    }
                }
                intent.putExtra("title", title)
                view.context.startActivity(intent)
            }
        }
    }
}