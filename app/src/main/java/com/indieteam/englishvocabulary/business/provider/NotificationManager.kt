package com.indieteam.englishvocabulary.business.provider

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.view.App
import javax.inject.Inject

class NotificationManager {

    @Inject
    constructor() {
        RemindService.serviceComponent.inject(this)
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannelProvider.createTestChannel()
            if (notificationChannelProvider.testChannelIsInitialized())
                notificationManager.createNotificationChannel(notificationChannelProvider.testChannel)

            notificationChannelProvider.createRemindChannel()
            if (notificationChannelProvider.remindChannelIsInitialized())
                notificationManager.createNotificationChannel(notificationChannelProvider.remindChannel)

            notificationChannelProvider.createForegroundChannel()
            if (notificationChannelProvider.foregroundChannelInitialized())
                notificationManager.createNotificationChannel(notificationChannelProvider.foregroundChannel)
        }
    }

    @Inject
    lateinit var context: Context
    @Inject
    lateinit var databaseManager: DatabaseManager
    @Inject
    lateinit var notificationChannelProvider: NotificationChannelProvider
    private var notificationManager: NotificationManager

    inner class TestNotification {
        private lateinit var builder: NotificationCompat.Builder
        private fun testNotification() {
            builder = NotificationCompat.Builder(context, NotificationChannelProvider.testChannelId)

            builder.apply {
                setSmallIcon(R.drawable.icon)
                    .setContentTitle("Remind for you")
                    .setContentText("Test notification")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true) //true: auto remove notification when user tap
            }
        }

        fun show() {
            testNotification()
            NotificationManagerCompat.from(context)
                .notify(NotificationChannelProvider.testNotificationId, builder.build())
        }
    }

    inner class RemindNotification {
        private lateinit var builder: NotificationCompat.Builder
        private fun remindNotification() {
            val randomVocabulary = databaseManager.randomVocabulary()
            builder =
                NotificationCompat.Builder(context, NotificationChannelProvider.remindChannelId)

            builder.apply {
                setSmallIcon(R.drawable.icon)
                    .setContentTitle(
                        randomVocabulary?.vocabulary?.capitalize() ?: "Try translate now"
                    )
                    .setContentText(randomVocabulary?.vi?.capitalize() ?: "")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(false) //true: auto remove notification when user tap
            }
        }

        fun show() {
            remindNotification()
            NotificationManagerCompat.from(context)
                .notify(NotificationChannelProvider.remindNotificationId, builder.build())
        }
    }

    inner class ForegroundNotification {
        private lateinit var builder: NotificationCompat.Builder
        private fun foregroundNotification() {
            builder =
                NotificationCompat.Builder(context, NotificationChannelProvider.foregroundChannelId)

            builder.apply {
                setSmallIcon(R.drawable.icon)
                    .setContentTitle("Keep remind for you")
                    .setContentText("I'm working")
                    .setSmallIcon(R.drawable.foreground_icon)
                    .setPriority(NotificationCompat.PRIORITY_MIN)
            }
        }

        fun build(): Notification {
            foregroundNotification()
            return builder.build()
        }
    }
}