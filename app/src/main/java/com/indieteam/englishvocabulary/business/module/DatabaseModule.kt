package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import com.indieteam.englishvocabulary.business.provider.DatabaseProvider
import dagger.Module

@Module
class DatabaseModule(private val databaseProvider: DatabaseProvider, private val databaseManager: DatabaseManager){

    fun getDatabadeProvider(): DatabaseProvider{
        return databaseProvider
    }

    fun getDatabaseManager(): DatabaseManager{
        return databaseManager
    }
}