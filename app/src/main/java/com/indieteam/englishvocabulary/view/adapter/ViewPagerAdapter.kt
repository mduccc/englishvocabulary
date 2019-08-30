package com.indieteam.englishvocabulary.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager) {

    val listLayout = ArrayList<Fragment>()

    override fun getItem(p0: Int): Fragment {
        return listLayout[p0]
    }

    override fun getCount(): Int {
        return listLayout.size
    }

}