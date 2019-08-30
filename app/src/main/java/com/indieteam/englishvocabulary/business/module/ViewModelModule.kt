package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.viewmodel.FavouriteViewModel
import com.indieteam.englishvocabulary.viewmodel.TensesRouterViewModel
import com.indieteam.englishvocabulary.viewmodel.TensesViewModel
import com.indieteam.englishvocabulary.viewmodel.TranslateViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun getTranslateViewModel(): TranslateViewModel {
        return TranslateViewModel()
    }

    @Provides
    fun getTensesViewModel(): TensesViewModel{
        return TensesViewModel()
    }

    @Provides
    fun getTensesRouterViewModel(): TensesRouterViewModel {
        return TensesRouterViewModel()
    }

    @Provides
    fun getFavouriteViewModel(): FavouriteViewModel {
        return FavouriteViewModel()
    }
}