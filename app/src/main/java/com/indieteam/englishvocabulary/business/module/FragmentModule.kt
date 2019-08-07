package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.view.FavouriteFragment
import com.indieteam.englishvocabulary.view.TensesFragment
import com.indieteam.englishvocabulary.view.TranslateFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {
    @Provides
    fun getFavouriteFragment(): FavouriteFragment{
        return FavouriteFragment()
    }

    @Provides
    fun getTensesFragment(): TensesFragment{
        return TensesFragment()
    }

    @Provides
    fun getTranslateFragment(): TranslateFragment {
        return TranslateFragment()
    }
}