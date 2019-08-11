package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.RemindService
import com.indieteam.englishvocabulary.business.provider.ServiceState
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {
    @Provides
    fun getServiceState(): ServiceState{
        return ServiceState()
    }

    @Provides
    fun getRemindService(): RemindService{
        return RemindService()
    }
}