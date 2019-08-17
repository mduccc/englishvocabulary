package com.indieteam.englishvocabulary

import android.util.Log
import androidx.test.runner.AndroidJUnit4
import com.indieteam.englishvocabulary.business.provider.RandomProvider
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RandomUnitTest {

    @Test
    fun random () {
        val random = RandomProvider()
        Log.d("random", random.randomID())
    }
}