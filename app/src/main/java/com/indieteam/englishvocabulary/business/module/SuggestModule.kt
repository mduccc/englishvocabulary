package com.indieteam.englishvocabulary.business.module

import android.content.Context
import com.indieteam.englishvocabulary.business.provider.SuggestProvider
import dagger.Module
import dagger.Provides

@Module
class SuggestModule {

    @Provides
    fun getSuggestProvider(context: Context): SuggestProvider {
        return SuggestProvider(context)
    }

}