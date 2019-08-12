package com.indieteam.englishvocabulary.business.component

import com.indieteam.englishvocabulary.business.module.*
import com.indieteam.englishvocabulary.business.provider.*
import com.indieteam.englishvocabulary.view.*
import com.indieteam.englishvocabulary.viewmodel.FavouriteViewModel
import com.indieteam.englishvocabulary.viewmodel.SettingsViewModel
import com.indieteam.englishvocabulary.viewmodel.TranslateViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, SuggestModule::class, RetrofitModule::class,
    TranslateModule::class, DatabaseModule::class, ViewModelModule::class, FragmentModule::class,
AdapterModule::class, RemindModule::class, NotificationModule::class])
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
    fun inject(settingsViewModel: SettingsViewModel)

    @Singleton
    fun inject(translateProvider: TranslateProvider)
    @Singleton
    fun inject(translateModelProvider: TranslateModelProvider)
    @Singleton
    fun inject(remindProvider: RemindProvider)
    @Singleton
    fun inject(notificationManager: NotificationManager)
    @Singleton
    fun inject(notificationChannelProvider: NotificationChannelProvider)
    @Singleton
    fun inject(serviceState: ServiceState)
    @Singleton
    fun inject(remindService: RemindService)
    @Singleton
    fun inject(deviceBoot: DeviceBoot)
}
