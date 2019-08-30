package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.view.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val mainActivity: MainActivity) {

    @Provides
    fun getMainActivityContextModule(): MainActivity {
        return mainActivity
    }
}