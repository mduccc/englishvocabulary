package com.indieteam.englishvocabulary.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.business.module.FragManagerModule
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
    }

    override fun onFailure() {
        download_model_loading.visibility = VISIBLE
        download_model_state.text = "Failure"
    }

    constructor() {
        App.module.fragManagerModule(FragManagerModule(supportFragmentManager))
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

    private val listLayout = ArrayList<Fragment>()
    @Inject
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
