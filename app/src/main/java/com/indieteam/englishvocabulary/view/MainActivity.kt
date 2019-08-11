package com.indieteam.englishvocabulary.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import com.indieteam.englishvocabulary.business.provider.RemindService
import com.indieteam.englishvocabulary.business.provider.ServiceState
import com.indieteam.englishvocabulary.business.provider.TranslateModelProvider
import com.indieteam.englishvocabulary.view.adapter.ViewPagerAdapter
import com.indieteam.englishvocabulary.view.update.OnDownloadModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity, OnDownloadModel {
    override fun onDownload() {
        download_model_loading.visibility = VISIBLE
    }

    override fun onSuccess() {
        download_model_loading.visibility = GONE
        if (!serviceState.remindServiceIsRunning()) {
            Log.d("RemindService", "Stating")
            startService(Intent(this, remindService.javaClass))
        }
    }

    override fun onFailure() {
        download_model_loading.visibility = VISIBLE
        download_model_state.text = "Failure"
    }

    constructor() {
        if (!App.isAppComponentInitialized())
            App.appComponent = App.module.build()

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
    lateinit var serviceState: ServiceState
    @Inject
    lateinit var remindService: RemindService
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
            add(tensesFragment)
            add(translateFragment)
            add(favouriteFragment)
        }

        viewPagerAdapter.listLayout.addAll(listLayout)

        view_pager.apply {
            adapter = viewPagerAdapter
            currentItem = 1
            offscreenPageLimit = 3
        }
    }

}
