package com.indieteam.englishvocabulary.view

import android.app.Application
import com.indieteam.englishvocabulary.business.component.AppComponent
import com.indieteam.englishvocabulary.business.component.DaggerAppComponent
import com.indieteam.englishvocabulary.business.component.DaggerServiceComponent
import com.indieteam.englishvocabulary.business.component.ServiceComponent
import com.indieteam.englishvocabulary.business.module.*
import java.lang.Exception

open class App : Application() {

    companion object{
        lateinit var appComponent: AppComponent
        lateinit var appModule: DaggerAppComponent.Builder
        fun isAppComponentInitialized() = ::appComponent.isInitialized

        lateinit var serviceModule: DaggerServiceComponent.Builder
        lateinit var serviceComponent: ServiceComponent
        fun isServiceComponentInitialized() = ::serviceComponent.isInitialized
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

    private fun setServiceModuleDefault() {
            serviceModule = DaggerServiceComponent.builder()
                .applicationContextModule(ApplicationContextModule(applicationContext))
                .databaseModule(DatabaseModule())
                .remindModule(RemindModule())
                .notificationModule(NotificationModule())
    }

    override fun onCreate() {
        super.onCreate()
        try {
            setAppModuleDefault()
            setServiceModuleDefault()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}