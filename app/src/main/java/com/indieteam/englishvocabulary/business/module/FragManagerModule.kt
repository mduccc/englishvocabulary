package com.indieteam.englishvocabulary.business.module

import android.support.v4.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
class FragManagerModule(private val supportFragmentManager: FragmentManager) {
    @Provides
    fun getSupportFragmentManager(): FragmentManager {
        return supportFragmentManager
    }
}