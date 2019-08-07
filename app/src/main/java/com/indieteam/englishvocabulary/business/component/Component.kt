package com.indieteam.englishvocabulary.business.component

import com.indieteam.englishvocabulary.business.module.*
import com.indieteam.englishvocabulary.business.provider.RetrofitProvider
import com.indieteam.englishvocabulary.business.provider.TranslateProvider
import com.indieteam.englishvocabulary.view.*
import com.indieteam.englishvocabulary.viewmodel.FavouriteViewModel
import com.indieteam.englishvocabulary.viewmodel.TranslateViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, SuggestModule::class, RetrofitModule::class,
    TranslateModule::class, DatabaseModule::class, ViewModelModule::class, FragmentModule::class,
AdapterModule::class, FragManagerModule::class])
interface AppComponent {
    @Singleton
    fun inject(fragment: TranslateFragment)
    @Singleton
    fun inject(fragment: FavouriteFragment)
    @Singleton fun inject(fragment: TensesFragment)
    @Singleton
    fun inject(activity: TensesActivity)
    @Singleton
    fun inject(activity: MainActivity)

    @Singleton
    fun inject(translateViewModel: TranslateViewModel)
    @Singleton
    fun inject(favouriteViewModel: FavouriteViewModel)

    @Singleton
    fun inject(translateProvider: TranslateProvider)
}
