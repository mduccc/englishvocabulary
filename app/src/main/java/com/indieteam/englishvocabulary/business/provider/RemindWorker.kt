package com.indieteam.englishvocabulary.business.provider

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.indieteam.englishvocabulary.view.App
import javax.inject.Inject

class RemindWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var remindProvider: RemindProvider

    override fun doWork(): Result {
        remindProvider.doRemind()
        return Result.success()
    }

}