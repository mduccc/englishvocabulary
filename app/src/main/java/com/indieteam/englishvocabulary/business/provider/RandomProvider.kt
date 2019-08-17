package com.indieteam.englishvocabulary.business.provider

class RandomProvider {

    private fun numberRandom(size: Int): Int {
        return (0..size).random()
    }

    fun randomID(): String {
        val az = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789"
        val length = 256
        var result = ""

        //Random
        for (i in 0 until length) {
            result += az[numberRandom(az.length - 1)].toString()
        }

        return result
    }
}