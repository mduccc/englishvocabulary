package com.indieteam.englishvocabulary.business.provider

import android.util.Log
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class RemindProvider {

    @Inject
    constructor() {
        RemindService.serviceComponent.inject(this)
    }

    @Inject
    lateinit var notificationManager: NotificationManager
    @Inject
    lateinit var databaseManager: DatabaseManager
    private var rateSetting: Int? = null
    private var timesRemind = ArrayList<String>()

    fun doRemind() {
        Log.d("doRemind", "doRemind")
        if (databaseManager.getFavorites().isEmpty())
            return
        rateSetting = databaseManager.getRateSetting()
        rateSetting?.let { _ ->
            Log.d("rateSetting", rateSetting.toString())
            when (rateSetting) {
                3 -> {
                    timesRemind.clear()
                    timesRemind.add("8")
                    timesRemind.add("10")
                    timesRemind.add("20")
                }
                4 -> {
                    timesRemind.clear()
                    timesRemind.add("8")
                    timesRemind.add("10")
                    timesRemind.add("20")
                    timesRemind.add("22")
                }
                5 -> {
                    timesRemind.clear()
                    timesRemind.add("8")
                    timesRemind.add("10")
                    timesRemind.add("12")
                    timesRemind.add("20")
                    timesRemind.add("22")
                }
                6 -> {
                    timesRemind.clear()
                    timesRemind.add("8")
                    timesRemind.add("10")
                    timesRemind.add("12")
                    timesRemind.add("15")
                    timesRemind.add("20")
                    timesRemind.add("22")
                }
                7 -> {
                    timesRemind.clear()
                    timesRemind.add("8")
                    timesRemind.add("10")
                    timesRemind.add("12")
                    timesRemind.add("15")
                    timesRemind.add("17")
                    timesRemind.add("20")
                    timesRemind.add("22")
                }
                8 -> {
                    timesRemind.clear()
                    timesRemind.add("6")
                    timesRemind.add("8")
                    timesRemind.add("10")
                    timesRemind.add("12")
                    timesRemind.add("15")
                    timesRemind.add("17")
                    timesRemind.add("20")
                    timesRemind.add("22")
                }
                9 -> {
                    timesRemind.clear()
                    timesRemind.add("6")
                    timesRemind.add("8")
                    timesRemind.add("10")
                    timesRemind.add("12")
                    timesRemind.add("14")
                    timesRemind.add("16")
                    timesRemind.add("18")
                    timesRemind.add("20")
                    timesRemind.add("22")
                }
                10 -> {
                    timesRemind.clear()
                    timesRemind.add("6")
                    timesRemind.add("8")
                    timesRemind.add("10")
                    timesRemind.add("12")
                    timesRemind.add("14")
                    timesRemind.add("16")
                    timesRemind.add("18")
                    timesRemind.add("20")
                    timesRemind.add("21")
                    timesRemind.add("22")
                }
                11 -> {
                    timesRemind.clear()
                    timesRemind.add("6")
                    timesRemind.add("8")
                    timesRemind.add("10")
                    timesRemind.add("12")
                    timesRemind.add("14")
                    timesRemind.add("16")
                    timesRemind.add("18")
                    timesRemind.add("19")
                    timesRemind.add("20")
                    timesRemind.add("21")
                    timesRemind.add("22")
                }
                12 -> {
                    timesRemind.clear()
                    timesRemind.add("6")
                    timesRemind.add("7")
                    timesRemind.add("8")
                    timesRemind.add("10")
                    timesRemind.add("12")
                    timesRemind.add("14")
                    timesRemind.add("16")
                    timesRemind.add("18")
                    timesRemind.add("19")
                    timesRemind.add("20")
                    timesRemind.add("21")
                    timesRemind.add("22")
                }
            }

            val calendar = Calendar.getInstance()
            val kk = calendar.get(Calendar.HOUR_OF_DAY)
            Log.d("kk", "$kk")
            if (timesRemind.isNotEmpty()) {
                val exits = timesRemind.filter { it == kk.toString() }
                Log.d("sizeOfTimesRemind", exits.size.toString())
                if (exits.isNotEmpty())
                    notificationManager.RemindNotification().show()
            } else {
                if (kk in 5..22)
                    notificationManager.RemindNotification().show()
            }
        }
    }
}
