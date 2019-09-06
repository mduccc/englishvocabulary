package com.indieteam.englishvocabulary

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.indieteam.englishvocabulary.business.provider.DatabaseManager
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DBUnitTest {

    @Test
    fun getVocabularySynced() {
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.indieteam.englishvocabulary", appContext.packageName)

        val databaseManager = DatabaseManager(appContext)
        val vocabulary = "hassle"
        //databaseManager.updateVocabularySyncStateName(vocabulary, true)
        databaseManager.getVocabularySyncedByName(vocabulary)
    }

    @Test
    fun getFavoritesNotSynced() {
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.indieteam.englishvocabulary", appContext.packageName)

        val databaseManager = DatabaseManager(appContext)
        databaseManager.getFavoritesNotSynced()
    }
}
