package com.indieteam.englishvocabulary.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.databinding.ActivitySettingsBinding
import com.indieteam.englishvocabulary.viewmodel.SettingsViewModel


class SettingsActivity : AppCompatActivity() {

    companion object{
        const val googleSignInCode = 10
    }

    private val settingsViewModel = SettingsViewModel()
    private lateinit var email: String
    private fun emailIsInitialized() = ::email.isInitialized


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySettingsBinding>(this, R.layout.activity_settings)
        binding.settingsViewModel = settingsViewModel
        binding.executePendingBindings()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == googleSignInCode && resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)

            account?.email?.let {
                email = it
            }
        }

        if (emailIsInitialized()) {
            Log.d("Email Logged", email)
        } else {
            Log.d("Email Logged", "null")
        }
    }
}
