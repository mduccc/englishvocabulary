package com.indieteam.englishvocabulary.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.indieteam.englishvocabulary.BR
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
            else -> {

            }
        }
    }

    private var times3 = false
    private var times4 = false
    private var times5 = false
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