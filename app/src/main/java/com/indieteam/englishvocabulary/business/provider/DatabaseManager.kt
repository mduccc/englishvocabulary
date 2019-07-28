package com.indieteam.englishvocabulary.business.provider

import android.content.ContentValues
import android.content.Context
import javax.inject.Singleton

@Singleton
class DatabaseManager(context: Context) : DatabaseProvider(context){

    fun insert(vocabulary: String, vi: String, description: String?){
        val writableDB = writableDatabase
        val value = ContentValues()
        value.put("vocabulary", vocabulary)
        value.put("vi", vi)
        value.put("description", description)

        writableDB.insert("vocabularies", null, value)
    }

    fun delete(id: String){
        val writableDB = writableDatabase
        writableDB.delete("vocabularies", "id?", arrayOf(id))
    }

}