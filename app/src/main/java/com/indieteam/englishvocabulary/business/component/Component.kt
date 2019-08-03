package com.indieteam.englishvocabulary.business.component

import com.indieteam.englishvocabulary.business.module.*
import com.indieteam.englishvocabulary.business.provider.RetrofitProvider
import com.indieteam.englishvocabulary.view.TranslateFragment
import com.indieteam.englishvocabulary.viewmodel.FavouriteViewModel
import com.indieteam.englishvocabulary.viewmodel.TranslateViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, SuggestModule::class, RetrofitModule::class, TranslateModule::class, DatabaseModule::class])
interface TranslateComponent {
    @Singleton
    fun poke(fragment: TranslateFragment)
    @Singleton
    fun poke(translateViewModel: TranslateViewModel)
}

@Component(modules = [ContextModule::class, DatabaseModule::class])
interface FavouruteComponent {
    @Singleton
    fun poke(favouriteViewModel: FavouriteViewModel)
}