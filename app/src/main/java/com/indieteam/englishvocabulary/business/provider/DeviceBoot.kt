package com.indieteam.englishvocabulary.business.provider

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.indieteam.englishvocabulary.view.App
import javax.inject.Inject

class DeviceBoot : BroadcastReceiver {

    @Inject
    constructor(){
    }

    @Inject
    lateinit var remindService: RemindService
    @Inject
    lateinit var serviceState: ServiceState

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1?.action == Intent.ACTION_BOOT_COMPLETED) {
            if (!App.isAppComponentInitialized())
                App.appComponent = App.module.build()
            App.appComponent.inject(this)

            if (!serviceState.remindServiceIsRunning()) {
                try {
                    Log.d("RemindService", "Stating")
                    RemindService.enqueueWork(p0!!)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun destroy(context: Context) {
        context.unregisterReceiver(this)
    }
}