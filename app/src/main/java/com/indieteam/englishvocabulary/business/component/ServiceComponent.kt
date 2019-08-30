package com.indieteam.englishvocabulary.business.component

import com.indieteam.englishvocabulary.business.module.ApplicationContextModule
import com.indieteam.englishvocabulary.business.module.DatabaseModule
import com.indieteam.englishvocabulary.business.module.NotificationModule
import com.indieteam.englishvocabulary.business.module.RemindModule
import com.indieteam.englishvocabulary.business.provider.*
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApplicationContextModule::class, RemindModule::class, DatabaseModule::class, NotificationModule::class])
interface ServiceComponent{
    @Singleton
    fun inject(remindService: RemindService)
    @Singleton
    fun inject(remindProvider: RemindProvider)
    @Singleton
    fun inject(remindWorker: RemindWorker)
    @Singleton
    fun inject(notificationManager: NotificationManager)
    @Singleton
    fun inject(remindForegroundService: RemindForegroundService)
}