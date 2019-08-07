package com.indieteam.englishvocabulary.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager) {

    val listLayout = ArrayList<Fragment>()

    override fun getItem(p0: Int): Fragment {
        return listLayout[p0]
    }

    override fun getCount(): Int {
        return listLayout.size
    }

}