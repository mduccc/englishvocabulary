package com.indieteam.englishvocabulary.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.view.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val translateFragment = TranslateFragment()
    private val favouriteFragment = FavouriteFragment()
    private val tensesFragment = TensesFragment()
    private val listLayout = ArrayList<Fragment>()
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listLayout.apply {
            add(tensesFragment)
            add(translateFragment)
            add(favouriteFragment)
        }

        viewPagerAdapter =
            ViewPagerAdapter(supportFragmentManager, listLayout)
        view_pager.adapter = viewPagerAdapter

        view_pager.currentItem = 1
        view_pager.offscreenPageLimit = 3
    }

}
