package com.indieteam.englishvocabulary.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import com.indieteam.englishvocabulary.business.provider.FirebaseDatabaseManager
import com.indieteam.englishvocabulary.business.provider.RandomProvider
import com.indieteam.englishvocabulary.databinding.ActivitySettingsBinding
import com.indieteam.englishvocabulary.model.AccountModel
import com.indieteam.englishvocabulary.viewmodel.SettingsViewModel
import javax.inject.Inject


class SettingsActivity : AppCompatActivity {

    constructor() {
        App.appComponent.inject(this)
    }

    companion object {
        const val googleSignInCode = 10
    }

    val settingsViewModel = SettingsViewModel()
    private lateinit var email: String
    private fun emailIsInitialized() = ::email.isInitialized

    @Inject
    lateinit var databaseManager: DatabaseManager
    @Inject
    lateinit var firebaseDatabaseManager: FirebaseDatabaseManager
    @Inject
    lateinit var randomProvider: RandomProvider
    @Inject
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySettingsBinding>(this, R.layout.activity_settings)
        binding.settingsViewModel = settingsViewModel
        binding.executePendingBindings()

        val account = GoogleSignIn.getLastSignedInAccount(applicationContext)

        account?.email?.let {
            settingsViewModel.setLinkTo("$it\n(cLick to unlink)")
            settingsViewModel.setLoginedOrLogouted(false)
            Log.d("Email logged", it)

        } ?: run {
            settingsViewModel.setLinkTo("Link with Google to sync data")
            settingsViewModel.setLoginedOrLogouted(true)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == googleSignInCode && resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)

            account?.email?.let {
                email = it
            }
            if (emailIsInitialized()) {
                Log.d("Email Logged", email)
                settingsViewModel.actionWithAccount = true
                settingsViewModel.setLinkTo("$email\n(cLick to unlink)")
                settingsViewModel.setLoginedOrLogouted(false)
                val accountModel = AccountModel(email, randomProvider.randomID(), "", "")
                firebaseDatabaseManager.insertAccount(accountModel)
                Toast.makeText(this, "Linked", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("Email Logged", "null")
            }
        }
    }

    override fun onDestroy() {
        if (settingsViewModel.actionWithAccount) {
            Log.d("onDestroy", "onDestroy SettingActivity")
            mainActivity.refresh()
        }
        super.onDestroy()
    }
}
