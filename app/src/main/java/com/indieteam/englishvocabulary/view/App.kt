package com.indieteam.englishvocabulary.view

import android.app.Application
import com.indieteam.englishvocabulary.business.component.AppComponent
import com.indieteam.englishvocabulary.business.component.DaggerAppComponent
import com.indieteam.englishvocabulary.business.module.*

open class App : Application() {

    companion object{
        lateinit var appComponent: AppComponent
        lateinit var module: DaggerAppComponent.Builder
        fun isAppComponentInitialized() = ::appComponent.isInitialized
    }

    override fun onCreate() {
        super.onCreate()

        module = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .suggestModule(SuggestModule())
            .retrofitModule(RetrofitModule())
            .translateModule(TranslateModule())
            .databaseModule(DatabaseModule())
            .viewModelModule(ViewModelModule())
            .fragmentModule(FragmentModule())
            .adapterModule(AdapterModule())
            .notificationModule(NotificationModule())
    }
}