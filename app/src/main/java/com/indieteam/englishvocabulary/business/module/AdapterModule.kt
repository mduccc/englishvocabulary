package com.indieteam.englishvocabulary.business.module

import androidx.fragment.app.FragmentManager
import com.indieteam.englishvocabulary.view.adapter.FavouriteAdapter
import com.indieteam.englishvocabulary.view.adapter.ViewPagerAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AdapterModule {
    @Provides
    fun getFavouriteAdapter(): FavouriteAdapter {
        return FavouriteAdapter()
    }

    @Provides
    fun getViewPagerAdapter(supportFragmentManager: FragmentManager): ViewPagerAdapter {
        return ViewPagerAdapter(supportFragmentManager)
    }
}