package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.RandomProvider
import dagger.Module
import dagger.Provides

@Module
class RandomModule {

    @Provides
    fun getRandom(): RandomProvider {
        return RandomProvider()
    }
}