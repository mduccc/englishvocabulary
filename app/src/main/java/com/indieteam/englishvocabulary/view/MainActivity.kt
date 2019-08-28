package com.indieteam.englishvocabulary.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.business.module.MainActivityModule
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import com.indieteam.englishvocabulary.business.provider.RemindWorker
import com.indieteam.englishvocabulary.business.provider.TranslateModelProvider
import com.indieteam.englishvocabulary.view.adapter.ViewPagerAdapter
import com.indieteam.englishvocabulary.view.update.OnDownloadModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MainActivity : AppCompatActivity, OnDownloadModel {
    override fun onDownload() {
        download_model_loading.visibility = VISIBLE
    }

    override fun onSuccess() {
        download_model_loading.visibility = GONE
        val periodicWorkRequest = PeriodicWorkRequest.Builder(RemindWorker::class.java, 1, TimeUnit.HOURS)
            .addTag("reminder")
            .build()
        // If you need to check already running work manager just because you don't want duplicate works. You can simply use enqueueUniquePeriodicWork())
        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork("reminder", ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest)
    }

    override fun onFailure() {
        download_model_loading.visibility = VISIBLE
        download_model_state.text = "Failure"
    }

    constructor() {
        App.appModule.mainActivityModule(MainActivityModule(this))
        App.appComponent = App.appModule.build()
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var translateFragment: TranslateFragment
    @Inject
    lateinit var favouriteFragment: FavouriteFragment
    @Inject
    lateinit var tensesFragment: TensesFragment
    @Inject
    lateinit var translateModelProvider: TranslateModelProvider
    @Inject
    lateinit var databaseManager: DatabaseManager

    private val listLayout = ArrayList<Fragment>()
    private val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseManager.insertRateSettingDefault()

        translateModelProvider.download(this)

        listLayout.apply {
            add(translateFragment)
            add(favouriteFragment)
            add(tensesFragment)
        }

        viewPagerAdapter.listLayout.addAll(listLayout)

        view_pager.apply {
            adapter = viewPagerAdapter
            currentItem = 1
            offscreenPageLimit = 3
        }
    }

    fun refresh() {
        try {
            favouriteFragment.apply {
                setRefresh()
                onRefresh()
            }

            translateFragment.translateViewModel.apply {
                setInputText("")
                setResultText("")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        if (favouriteFragment.favouriteAdapter.isTssIsInitialized())
            favouriteFragment.favouriteAdapter.tts.shutdown()
        super.onDestroy()
        //exitProcess(0)
    }
}
