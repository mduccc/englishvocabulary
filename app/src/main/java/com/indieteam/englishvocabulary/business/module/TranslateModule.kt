package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.TranslateProvider
import dagger.Module
import dagger.Provides

@Module
class TranslateModule {

    @Provides
    fun getTranslateProvider(): TranslateProvider {
        return TranslateProvider()
    }

}