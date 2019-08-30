package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.RemindForegroundService
import com.indieteam.englishvocabulary.business.provider.RemindProvider
import com.indieteam.englishvocabulary.business.provider.RemindService
import com.indieteam.englishvocabulary.business.provider.ServiceState
import dagger.Module
import dagger.Provides

@Module
class RemindModule {

    @Provides
    fun getReminderProvider(): RemindProvider {
        return RemindProvider()
    }

    @Provides
    fun getRemindWorker(): RemindService{
        return RemindService()
    }

    @Provides
    fun getServiceState(): ServiceState {
        return ServiceState()
    }
}