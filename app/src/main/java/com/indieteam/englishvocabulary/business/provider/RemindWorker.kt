package com.indieteam.englishvocabulary.business.provider

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import javax.inject.Inject

class RemindWorker(context: Context, workerParameters: WorkerParameters): Worker(context, workerParameters) {

    @Inject
    lateinit var remindProvider: RemindProvider

    init {
        RemindService.initDI(applicationContext)
        RemindService.serviceComponent.inject(this@RemindWorker)
    }

    override fun doWork(): Result {
        Log.d("doWork", "doWork")
        remindProvider.doRemind()

        return Result.success()
    }
}