package com.indieteam.englishvocabulary.view

import android.app.Application
import com.indieteam.englishvocabulary.business.component.AppComponent
import com.indieteam.englishvocabulary.business.component.DaggerAppComponent
import com.indieteam.englishvocabulary.business.module.*
import java.lang.Exception

open class App : Application() {

    companion object{
        lateinit var appComponent: AppComponent
        lateinit var appModule: DaggerAppComponent.Builder
        fun isAppComponentInitialized() = ::appComponent.isInitialized

    }

    private fun setAppModuleDefault() {
        appModule = DaggerAppComponent.builder()
            .applicationContextModule(ApplicationContextModule(applicationContext))
            .suggestModule(SuggestModule())
            .retrofitModule(RetrofitModule())
            .translateModule(TranslateModule())
            .databaseModule(DatabaseModule())
            .viewModelModule(ViewModelModule())
            .fragmentModule(FragmentModule())
            .adapterModule(AdapterModule())
            .notificationModule(NotificationModule())
    }

    override fun onCreate() {
        super.onCreate()
        try {
            setAppModuleDefault()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}