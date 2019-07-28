package com.indieteam.englishvocabulary.business.module

import android.content.Context
import com.indieteam.englishvocabulary.business.provider.SuggestProvider
import dagger.Module
import dagger.Provides

@Module
class SuggestModule(private val suggestProvider: SuggestProvider) {

    @Provides
    fun getSuggestProvider(): SuggestProvider {
        return suggestProvider
    }

}