package com.indieteam.englishvocabulary.business.provider

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.work.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RemindForegroundService: Service() {

    @Inject
    lateinit var notificationManager: NotificationManager

    override fun onBind(p0: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()

        RemindService.apply {
            initDI(applicationContext)
            serviceComponent.inject(this@RemindForegroundService)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(NotificationChannelProvider.foregroundNotificationId, notificationManager.ForegroundNotification().build())

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(RemindWorker::class.java)
            .addTag("english_vocabulary_remind")
            .setInitialDelay(15, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniqueWork(RemindService.uniqueWorkName, ExistingWorkPolicy.REPLACE, oneTimeWorkRequest)
        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}