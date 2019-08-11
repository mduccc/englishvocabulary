package com.indieteam.englishvocabulary.viewmodel

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.indieteam.englishvocabulary.BR
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import com.indieteam.englishvocabulary.view.App
import javax.inject.Inject

class SettingsViewModel : BaseObservable {
    constructor() {
        App.appComponent.inject(this)

        val selected = dataBaseManager.getRateSetting()
        rateId = dataBaseManager.getRateId()

        when (selected) {
            3 -> setTimes3(true)
            4 -> setTimes4(true)
            5 -> setTimes5(true)
            else -> {

            }
        }
    }

    private var times3 = false
    private var times4 = false
    private var times5 = false

    @Inject
    lateinit var dataBaseManager: DatabaseManager
    var rateId: String? = ""

    @Bindable
    fun getTimes3(): Boolean {
        return times3
    }

    fun setTimes3(boolean: Boolean) {
        times3 = boolean
        if (boolean) {
            rateId?.let {
                dataBaseManager.updateRateSetting(it, 3)
            }
        }
        notifyPropertyChanged(BR.times3)
    }

    @Bindable
    fun getTimes4(): Boolean {
        return times4
    }

    fun setTimes4(boolean: Boolean) {
        times4 = boolean
        if (boolean) {
            rateId?.let {
                dataBaseManager.updateRateSetting(it, 4)
            }
        }
        notifyPropertyChanged(BR.times4)
    }

    @Bindable
    fun getTimes5(): Boolean {
        return times5
    }

    fun setTimes5(boolean: Boolean) {
        times5 = boolean
        if (boolean) {
            rateId?.let {
                dataBaseManager.updateRateSetting(it, 5)
            }
        }
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