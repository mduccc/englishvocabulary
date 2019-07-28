package com.indieteam.englishvocabulary.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewPagerAdapter(supporFragmentManager: FragmentManager, val listLayout: ArrayList<Fragment>) : FragmentStatePagerAdapter(supporFragmentManager) {
    override fun getItem(p0: Int): Fragment {
        return listLayout[p0]
    }

    override fun getCount(): Int {
        return listLayout.size
    }

}