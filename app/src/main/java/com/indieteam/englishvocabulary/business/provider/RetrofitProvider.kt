package com.indieteam.englishvocabulary.business.provider

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    fun builder(): EndPoint {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(UrlProvider.baseUrl)
            .build()

        return retrofit.create(EndPoint::class.java)
    }
}