package com.indieteam.englishvocabulary.business.provider

import android.app.ActivityManager
import android.content.Context
import android.util.Log
import com.indieteam.englishvocabulary.view.App
import javax.inject.Inject
import javax.inject.Singleton

class ServiceState {

    @Inject
    constructor() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var context: Context

    fun remindServiceIsRunning(): Boolean {
        var isRunning = false
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (service.service.className == RemindService::class.java.name)
                isRunning = true
        }

        if (isRunning)
            Log.d("RemindService", "Running")
        else
            Log.d("RemindService", "Stopped")

        return isRunning
    }
}