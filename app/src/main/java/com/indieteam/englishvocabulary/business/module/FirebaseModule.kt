package com.indieteam.englishvocabulary.business.module

import com.indieteam.englishvocabulary.business.provider.FirebaseDatabaseManager
import com.indieteam.englishvocabulary.business.provider.FirebaseDatabaseProvider
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {

    @Provides
    fun getFirebaseDatabaseManager(): FirebaseDatabaseManager {
        return FirebaseDatabaseManager()
    }

    @Provides
    fun getFirebaseDatabaseProvider(): FirebaseDatabaseProvider {
        return FirebaseDatabaseProvider()
    }
}