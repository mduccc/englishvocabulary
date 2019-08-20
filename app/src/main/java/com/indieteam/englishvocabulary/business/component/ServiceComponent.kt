package com.indieteam.englishvocabulary.business.component

import com.indieteam.englishvocabulary.business.module.ApplicationContextModule
import com.indieteam.englishvocabulary.business.module.DatabaseModule
import com.indieteam.englishvocabulary.business.module.NotificationModule
import com.indieteam.englishvocabulary.business.module.RemindModule
import com.indieteam.englishvocabulary.business.provider.NotificationManager
import com.indieteam.englishvocabulary.business.provider.RemindProvider
import com.indieteam.englishvocabulary.business.provider.RemindService
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApplicationContextModule::class, RemindModule::class, NotificationModule::class, DatabaseModule::class])
interface ServiceComponent {
    @Singleton
    fun inject(remindProvider: RemindProvider)

    @Singleton
    fun inject(remindService: RemindService)

    @Singleton
    fun inject(notificationManager: NotificationManager)
}