package com.indieteam.englishvocabulary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.databinding.ActivitySettingsBinding
import com.indieteam.englishvocabulary.viewmodel.SettingsViewModel

class SettingsActivity : AppCompatActivity() {

    private val settingsViewModel  = SettingsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySettingsBinding>(this, R.layout.activity_settings)
        binding.settingsViewModel = settingsViewModel
        binding.executePendingBindings()
    }
}
