package com.indieteam.englishvocabulary.business.provider

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.indieteam.englishvocabulary.view.App
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class RemindProvider : BroadcastReceiver {

    constructor() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var notificationManager: NotificationManager
    @Inject
    lateinit var databaseManager: DatabaseManager
    private var rateSetting: Int? = null
    private var timesRemind = ArrayList<String>()

    override fun onReceive(p0: Context?, p1: Intent?) {
        rateSetting = databaseManager.getRateSetting()
        rateSetting?.let {
            when (rateSetting) {
                3 -> {
                    timesRemind.clear()
                    timesRemind.add("8:0")
                    timesRemind.add("10:0")
                    timesRemind.add("20:0")
                }
                4 -> {
                    timesRemind.clear()
                    timesRemind.add("8:0")
                    timesRemind.add("10:0")
                    timesRemind.add("20:0")
                    timesRemind.add("22:0")
                }
                5 -> {
                    timesRemind.clear()
                    timesRemind.add("8:0")
                    timesRemind.add("10:0")
                    timesRemind.add("12:0")
                    timesRemind.add("20:0")
                    timesRemind.add("22:0")
                }
            }

            p1?.let { it ->
                if (it.action == Intent.ACTION_TIME_TICK) {
                    val calendar = Calendar.getInstance()
                    val kk = calendar.get(Calendar.HOUR_OF_DAY)
                    val mm = calendar.get(Calendar.MINUTE)
                    val kkmm = "$kk:$mm"
                    Log.d("kk:mm", kkmm)
                    val exits = timesRemind.filter { it == kkmm }
                    Log.d("sizeOfTimesRemind", exits.size.toString())
                    if (exits.isNotEmpty())
                        notificationManager.RemindNotification().show()
                }
            }

        }
    }

    fun register(context: Context) {
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