package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val databaseManager: DatabaseManager){

    @Provides
    fun getDatabaseManager(): DatabaseManager{
        return databaseManager
    }
}