package com.indieteam.englishvocabulary.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import com.indieteam.englishvocabulary.view.App
import com.indieteam.englishvocabulary.view.SettingsActivity
import javax.inject.Inject

class SettingsViewModel : BaseObservable {
    constructor() {
        App.appComponent.inject(this)

        val selected = databaseManager.getRateSetting()
        rateId = databaseManager.getRateId()

        when (selected) {
            3 -> setTimes3(true)
            4 -> setTimes4(true)
            5 -> setTimes5(true)
            6 -> setTimes6(true)
            7 -> setTimes7(true)
            8 -> setTimes8(true)
            9 -> setTimes9(true)
            10 -> setTimes10(true)
            11 -> setTimes11(true)
            12 -> setTimes12(true)
            else -> {
                setEveryhHour(true)
            }
        }
    }

    private var times3 = false
    private var times4 = false
    private var times5 = false
    private var times6 = false
    private var times7 = false
    private var times8 = false
    private var times9 = false
    private var times10 = false
    private var times11 = false
    private var times12 = false
    private var everyHour = false
    private var loginOrLogout = true
    private var linkTo = ""
    var actionWithAccount = false


    @Inject
    lateinit var databaseManager: DatabaseManager
    var rateId: String? = ""

    @Bindable
    fun getTimes3(): Boolean {
        return times3
    }

    fun setTimes3(boolean: Boolean) {
        times3 = boolean
        if (boolean) {
            rateId?.let {
                databaseManager.updateRateSetting(it, 3)
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
                databaseManager.updateRateSetting(it, 4)
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
                databaseManager.updateRateSetting(it, 5)
            }
        }
        notifyPropertyChanged(BR.times5)
    }

    @Bindable
    fun getTimes6(): Boolean {
        return times6
    }

    fun setTimes6(boolean: Boolean) {
        times6 = boolean
        if (boolean) {
            rateId?.let {
                databaseManager.updateRateSetting(it, 6)
            }
        }
        notifyPropertyChanged(BR.times6)
    }

    @Bindable
    fun getTimes7(): Boolean {
        return times7
    }

    fun setTimes7(boolean: Boolean) {
        times7 = boolean
        if (boolean) {
            rateId?.let {
                databaseManager.updateRateSetting(it, 7)
            }
        }
        notifyPropertyChanged(BR.times7)
    }

    @Bindable
    fun getTimes8(): Boolean {
        return times8
    }

    fun setTimes8(boolean: Boolean) {
        times8 = boolean
        if (boolean) {
            rateId?.let {
                databaseManager.updateRateSetting(it, 8)
            }
        }
        notifyPropertyChanged(BR.times8)
    }

    @Bindable
    fun getTimes9(): Boolean {
        return times9
    }

    fun setTimes9(boolean: Boolean) {
        times9 = boolean
        if (boolean) {
            rateId?.let {
                databaseManager.updateRateSetting(it, 9)
            }
        }
        notifyPropertyChanged(BR.times9)
    }

    @Bindable
    fun getTimes10(): Boolean {
        return times10
    }

    fun setTimes10(boolean: Boolean) {
        times10 = boolean
        if (boolean) {
            rateId?.let {
                databaseManager.updateRateSetting(it, 10)
            }
        }
        notifyPropertyChanged(BR.times10)
    }

    @Bindable
    fun getTimes11(): Boolean {
        return times11
    }

    fun setTimes11(boolean: Boolean) {
        times11 = boolean
        if (boolean) {
            rateId?.let {
                databaseManager.updateRateSetting(it, 11)
            }
        }
        notifyPropertyChanged(BR.times11)
    }

    @Bindable
    fun getTimes12(): Boolean {
        return times12
    }

    fun setTimes12(boolean: Boolean) {
        times12 = boolean
        if (boolean) {
            rateId?.let {
                databaseManager.updateRateSetting(it, 12)
            }
        }
        notifyPropertyChanged(BR.times12)
    }

    @Bindable
    fun getEveryHour(): Boolean {
        return everyHour
    }

    fun setEveryhHour(boolean: Boolean) {
        everyHour = boolean
        if (boolean) {
            rateId?.let {
                databaseManager.updateRateSetting(it, -1)
            }
        }
        notifyPropertyChanged(BR.everyHour)
    }


    @Bindable
    fun getLoginOrLogout(): Boolean {
        return loginOrLogout
    }

    fun setLoginOrLogout(boolean: Boolean) {
        loginOrLogout = boolean
        notifyPropertyChanged(BR.loginOrLogout)
    }

    @Bindable
    fun getLinkTo(): String {
        return linkTo
    }

    fun setLinkTo(string: String) {
        linkTo = string
        notifyPropertyChanged(BR.linkTo)
    }

    private fun selectAll(boolean: Boolean) {
        setTimes3(boolean)
        setTimes4(boolean)
        setTimes5(boolean)
        setTimes6(boolean)
        setTimes7(boolean)
        setTimes8(boolean)
        setTimes9(boolean)
        setTimes10(boolean)
        setTimes11(boolean)
        setTimes12(boolean)
        setEveryhHour(false)
    }

    fun times3Click() {
        selectAll(false)
        setTimes3(true)
    }

    fun times4Click() {
        selectAll(false)
        setTimes4(true)
    }

    fun times5Click() {
        selectAll(false)
        setTimes5(true)
    }

    fun times6Click() {
        selectAll(false)
        setTimes6(true)
    }

    fun times7Click() {
        selectAll(false)
        setTimes7(true)
    }

    fun times8Click() {
        selectAll(false)
        setTimes8(true)
    }

    fun times9Click() {
        selectAll(false)
        setTimes9(true)
    }

    fun times10Click() {
        selectAll(false)
        setTimes10(true)
    }

    fun times11Click() {
        selectAll(false)
        setTimes11(true)
    }

    fun times12Click() {
        selectAll(false)
        setTimes12(true)
    }

    fun everyHourClick() {
        selectAll(false)
        setEveryhHour(true)
    }



    companion object {
        private var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        @SuppressLint("StaticFieldLeak")
        private lateinit var mGoogleSignInClient: GoogleSignInClient
        private lateinit var email: String
        private fun emailIsInitialized() = ::email.isInitialized

        @BindingAdapter("googleLogin")
        @JvmStatic
        fun googleLogin(view: View, loginOrLogout: Boolean) {
            view.setOnClickListener {
                mGoogleSignInClient = GoogleSignIn.getClient(view.context.applicationContext, gso)
                if (loginOrLogout) {
                    // Login
                    val account = GoogleSignIn.getLastSignedInAccount(view.context.applicationContext)

                    account?.email?.let {
                        email = it
                    }

                    if (emailIsInitialized()) {
                        Log.d("Email Logged", email)
                    } else {
                        Log.d("Email Logged", "null")
                        val signInIntent = mGoogleSignInClient.signInIntent
                        (view.context as Activity).startActivityForResult(
                            signInIntent,
                            SettingsActivity.googleSignInCode
                        )
                    }
                } else {
                    // Logout
                    mGoogleSignInClient.signOut()
                        .addOnCompleteListener {
                            try {
                                (view.context as SettingsActivity).settingsViewModel.apply {
                                    setLoginOrLogout(true)
                                    setLinkTo("Link with Google to sync data")
                                    databaseManager.deleteAccount()
                                    databaseManager.deleteAllVocabulary()
                                    actionWithAccount = true
                                }
                                Toast.makeText(view.context, "Unlinked", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                }
            }
        }
    }
}