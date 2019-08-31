package com.indieteam.englishvocabulary.business.provider

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
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
        val periodicWorkRequest = PeriodicWorkRequest.Builder(RemindWorker::class.java, 1, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(RemindService.uniqueWorkName, ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest)
        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}