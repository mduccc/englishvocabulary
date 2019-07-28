package com.indieteam.englishvocabulary.business.component

import com.indieteam.englishvocabulary.business.module.ContextModule
import com.indieteam.englishvocabulary.business.module.RetrofitModule
import com.indieteam.englishvocabulary.business.module.SuggestModule
import com.indieteam.englishvocabulary.business.module.TranslateModule
import com.indieteam.englishvocabulary.business.provider.RetrofitProvider
import com.indieteam.englishvocabulary.view.TranslateFragment
import com.indieteam.englishvocabulary.viewmodel.TranslateViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, SuggestModule::class, RetrofitModule::class, TranslateModule::class])
interface AppComponent {
    @Singleton
    fun poke(fragment: TranslateFragment)
    @Singleton
    fun poke(translateViewModel: TranslateViewModel)
}