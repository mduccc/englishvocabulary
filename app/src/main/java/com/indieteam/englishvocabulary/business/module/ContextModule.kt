package com.indieteam.englishvocabulary.business.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    fun getContext(): Context {
        return context
    }
}