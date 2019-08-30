package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.RetrofitProvider
import dagger.Module
import dagger.Provides

@Module
class RetrofitModule {

    @Provides
    fun getRetrofitProvider() : RetrofitProvider{
        return RetrofitProvider
    }
}