package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.RetrofitProvider
import com.indieteam.englishvocabulary.business.provider.TranslateProvider
import dagger.Module
import dagger.Provides

@Module
class TranslateModule(private val translateProvider: TranslateProvider) {

    @Provides
    fun getTranslateProvider(): TranslateProvider {
        return translateProvider
    }


    @Provides
    fun getTranslateBuilder(retrofitProvider: RetrofitProvider): TranslateProvider.Builder{
        return translateProvider.Builder(retrofitProvider)
    }

}