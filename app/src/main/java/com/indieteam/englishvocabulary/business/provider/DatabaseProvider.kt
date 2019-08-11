package com.indieteam.englishvocabulary.business.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception
import javax.inject.Singleton

@Singleton
abstract class DatabaseProvider(context: Context) : SQLiteOpenHelper(context, "englishvocabulary", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        try {
            var sql = "CREATE TABLE IF NOT EXISTS favorites (id INTEGER PRIMARY KEY, vocabulary text, vi text, remindChannelDescription text)"
            p0?.execSQL(sql)
            sql = "CREATE TABLE IF NOT EXISTS account (id INTEGER PRIMARY KEY, email text, type text, remindChannelDescription text)"
            p0?.execSQL(sql)
            sql = "CREATE TABLE IF NOT EXISTS notification_settings (id INTEGER PRIMARY KEY, rate int)"
            p0?.execSQL(sql)
            Log.d("onCreateTable", "onCreate")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("onCreateTable", e.toString())
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}