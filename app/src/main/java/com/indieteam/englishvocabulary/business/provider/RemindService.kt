package com.indieteam.englishvocabulary.business.provider

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.indieteam.englishvocabulary.view.App
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

class RemindService : Service {

    @Inject
    constructor() {
        if (!App.isAppComponentInitialized())
            App.appComponent = App.module.build()
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var remindProvider: RemindProvider

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("RemindService", "Stated")
        remindProvider.register(applicationContext)
        return START_STICKY
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