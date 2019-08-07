package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.RemindService
import dagger.Module
import dagger.Provides

@Module
class RemindServiceModule {
    @Provides
    fun getRemindService(): RemindService{
        return RemindService()
    }
}