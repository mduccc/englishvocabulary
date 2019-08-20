package com.indieteam.englishvocabulary.business.provider

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import com.indieteam.englishvocabulary.view.App
import javax.inject.Inject


class RemindService : JobIntentService {

    companion object{
        const val job_id = 10000
        fun enqueueWork(context: Context) {
            enqueueWork(context, RemindService::class.java, job_id, Intent())
        }
    }

    @Inject
    constructor() {
        App.serviceComponent = App.serviceModule.build()
        App.serviceComponent.inject(this)
    }

    @Inject
    lateinit var remindProvider: RemindProvider

    // When running on Android O or later, the work will be dispatched as a job via JobScheduler.enqueue.
    // When running on older versions of the platform, it will use Context.startService
    override fun onHandleWork(intent: Intent) {
        try {
            Log.d("RemindService", "Stated")
            val _intent = Intent(applicationContext, RemindService::class.java)
            applicationContext.startService(_intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        remindProvider.register(applicationContext)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        Log.d("RemindService", "On Task Removed")
        enqueueWork(applicationContext)
        super.onTaskRemoved(rootIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("RemindService", "Stopping")
        Log.d("RemindService", "Stopped")
        try {
            remindProvider.destroy(applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}