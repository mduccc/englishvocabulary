package com.indieteam.englishvocabulary

import androidx.test.InstrumentationRegistry
import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.AndroidJUnit4
import com.indieteam.englishvocabulary.business.provider.TranslateProvider
import com.indieteam.englishvocabulary.view.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TranslateUnitTest {

    @Test
    fun translate() {
        getInstrumentation().runOnMainSync {
            // Context of the app under test.
            val appContext = InstrumentationRegistry.getTargetContext()
            assertEquals("com.indieteam.englishvocabulary", appContext.packageName)

            val mainActivity = MainActivity()
            val translateProvider = TranslateProvider()
            val hashTable = arrayOf("hello", "hel lo", "What have you lost mobile since last week?", "important")

            for (input in hashTable)
                translateProvider.Builder().offlineTranslate(mainActivity.translateFragment.translateViewModel, input)

        }
    }
}
