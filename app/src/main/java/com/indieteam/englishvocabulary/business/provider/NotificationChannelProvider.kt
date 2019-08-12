package com.indieteam.englishvocabulary.business.provider

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build


class NotificationChannelProvider {

    companion object{
        const val testNotificationId = 1
        const val testChannelId = "test_channel"
        const val testChannelName = "Test"
        const val testChannelDescription = "Test Channel"
        const val testChannelImportant = NotificationManager.IMPORTANCE_HIGH

        const val remindNotificationId = 2
        const val remindChannelId = "remind_channel"
        const val remindChannelName = "Remind"
        const val remindChannelDescription = "Remind Channel"
        const val remindChannelImportant = NotificationManager.IMPORTANCE_HIGH
    }

    lateinit var remindChannel: NotificationChannel
    lateinit var testChannel: NotificationChannel
    fun remindChannelIsInitialized() = ::remindChannel.isInitialized
    fun testChannelIsInitialized() = ::testChannel.isInitialized

    fun createTestChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            testChannel = NotificationChannel(testChannelId, testChannelName, testChannelImportant)
            testChannel.description = testChannelDescription
        }
    }

    fun createRemindChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            remindChannel = NotificationChannel(remindChannelId, remindChannelName, remindChannelImportant)
            remindChannel.description = remindChannelDescription
        }
    }

}
