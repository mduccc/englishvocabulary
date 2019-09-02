package com.indieteam.englishvocabulary.business.provider

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RemindWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    @Inject
    lateinit var remindProvider: RemindProvider

    init {
        RemindService.initDI(applicationContext)
        RemindService.serviceComponent.inject(this@RemindWorker)
    }

    override fun doWork(): Result {
        Log.d("doWork", "doWork")
        remindProvider.doRemind()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val oneTimeWorkRequest = OneTimeWorkRequest.Builder(RemindWorker::class.java)
                .addTag("english_vocabulary_remind")
                .setInitialDelay(1, TimeUnit.HOURS)
                .build()

            WorkManager.getInstance(applicationContext).enqueueUniqueWork(
                RemindService.uniqueWorkName,
                ExistingWorkPolicy.REPLACE,
                oneTimeWorkRequest
            )
        }

        return Result.success()
    }
}