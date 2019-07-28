package com.indieteam.englishvocabulary.business.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import javax.inject.Singleton

@Singleton
abstract class DatabaseProvider(context: Context) : SQLiteOpenHelper(context, "englishvocabulary", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS vocabularies (id INTEGER PRIMARY KEY, vocabulary text, vi text, description text)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}