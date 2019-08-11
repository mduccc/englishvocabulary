package com.indieteam.englishvocabulary.business.provider

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.indieteam.englishvocabulary.view.App
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemindProvider: BroadcastReceiver {

    constructor(){
        App.appComponent.inject(this)
    }

    override fun onReceive(p0: Context?, p1: Intent?) {
        p1?.let {
            if (it.action == Intent.ACTION_TIME_TICK) {
                val date = Date()
                Log.d("Time Now", date.time.toString())
            }
        }

    }

    fun register(context: Context){
        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_TIME_TICK)
        }
        context.registerReceiver(this, intentFilter)
    }

    fun destroy(context: Context) {
        context.unregisterReceiver(this)
    }
}