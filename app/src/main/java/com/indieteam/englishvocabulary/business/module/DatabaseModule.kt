package com.indieteam.englishvocabulary.business.module

import android.content.Context
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule{

    @Provides
    fun getDatabaseManager(context: Context): DatabaseManager{
        return DatabaseManager(context)
    }
}