package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.NotificationChannelProvider
import com.indieteam.englishvocabulary.business.provider.NotificationManager
import dagger.Module
import dagger.Provides

@Module
class NotificationModule {

    @Provides
    fun getNotificationChannelProvider(): NotificationChannelProvider{
        return NotificationChannelProvider()
    }

    @Provides
    fun getNotificationManager(): NotificationManager {
        return NotificationManager()
    }
}