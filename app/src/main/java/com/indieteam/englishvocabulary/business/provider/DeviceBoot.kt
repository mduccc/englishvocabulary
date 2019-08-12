package com.indieteam.englishvocabulary.business.provider

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import javax.inject.Inject

class DeviceBoot : BroadcastReceiver {

    @Inject
    constructor(){

    }

    override fun onReceive(p0: Context?, p1: Intent?) {
        p0?.startService(Intent(p0, RemindService::class.java))
    }

    fun destroy(context: Context) {
        context.unregisterReceiver(this)
    }
}