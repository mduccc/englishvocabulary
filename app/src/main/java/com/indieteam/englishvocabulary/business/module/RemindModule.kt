package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.RemindProvider
import dagger.Module
import dagger.Provides

@Module
class RemindModule {

    @Provides
    fun getReminderProvider(): RemindProvider {
        return RemindProvider()
    }
}