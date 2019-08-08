package com.indieteam.englishvocabulary.business.provider

import com.indieteam.englishvocabulary.model.TranslateModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoint {
    @GET("api/v1.5/tr.json/onlineTranslate")
    fun translate(@Query("text") text: String, @Query("format") format: String, @Query("lang") lang: String, @Query("key") key: String): Call<TranslateModel.Success>
}