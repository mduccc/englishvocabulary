package com.indieteam.englishvocabulary.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.indieteam.englishvocabulary.BR

class SettingsViewModel : BaseObservable {
    constructor() {

    }

    private var times3 = false
    private var times4 = true
    private var times5 = false


    @Bindable
    fun getTimes3(): Boolean {
        return times3
    }

    fun setTimes3(boolean: Boolean) {
        times3 = boolean
        notifyPropertyChanged(BR.times3)
    }

    @Bindable
    fun getTimes4(): Boolean {
        return times4
    }

    fun setTimes4(boolean: Boolean) {
        times4 = boolean
        notifyPropertyChanged(BR.times4)
    }

    @Bindable
    fun getTimes5(): Boolean {
        return times5
    }

    fun setTimes5(boolean: Boolean) {
        times5 = boolean
        notifyPropertyChanged(BR.times5)
    }

    fun times3Click() {
        setTimes3(true)
        setTimes4(false)
        setTimes5(false)
    }

    fun times4Click() {
        setTimes3(false)
        setTimes4(true)
        setTimes5(false)
    }

    fun times5Click() {
        setTimes3(false)
        setTimes4(false)
        setTimes5(true)
    }
}