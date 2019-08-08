package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.FirebaseTranslatorProvider
import com.indieteam.englishvocabulary.business.provider.TranslateModelProvider
import com.indieteam.englishvocabulary.business.provider.TranslateProvider
import dagger.Module
import dagger.Provides

@Module
class TranslateModule {

    @Provides
    fun getTranslateProvider(): TranslateProvider {
        return TranslateProvider()
    }

    @Provides
    fun getFirebaseTranslatorProvider(): FirebaseTranslatorProvider{
        return FirebaseTranslatorProvider()
    }

    @Provides
    fun getTranslateModelProvider(): TranslateModelProvider{
        return TranslateModelProvider()
    }
}