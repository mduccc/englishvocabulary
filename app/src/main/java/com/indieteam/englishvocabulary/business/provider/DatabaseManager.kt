package com.indieteam.englishvocabulary.business.provider

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.indieteam.englishvocabulary.model.FavouriteModel
import java.lang.Exception
import javax.inject.Singleton

@Singleton
class DatabaseManager(context: Context) : DatabaseProvider(context) {

    fun getFavorites(): ArrayList<FavouriteModel.item> {
        val result = ArrayList<FavouriteModel.item>()

        try {
            val readable = readableDatabase
            val cursor = readable.rawQuery("SELECT * FROM favorites ORDER BY id DESC", null)
            cursor.moveToFirst()

            while (!cursor.isAfterLast && cursor != null) {
                val item = FavouriteModel.item(
                    cursor.getString(cursor.getColumnIndex("id")).toInt(),
                    cursor.getString(cursor.getColumnIndex("vocabulary")),
                    cursor.getString(cursor.getColumnIndex("vi"))
                )
                result.add(item)
                Log.d("data", "${item.id} ${item.vocabulary} ${item.vi}")
                cursor.moveToNext()
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }

    fun insert(vocabulary: String, vi: String, description: String?): Boolean {
        var result = true

        try {
            if (isExists(vocabulary))
                return false

            val writableDB = writableDatabase
            val value = ContentValues()
            value.put("vocabulary", vocabulary)
            value.put("vi", vi)
            value.put("description", description)

            writableDB.insert("favorites", null, value)

        } catch (e: Exception) {
            e.printStackTrace()
            result = false
        }

        return result
    }

    fun delete(vocabulary: String): Boolean {
        var result = true

        try {
            if (!isExists(vocabulary))
                return false

            val writableDB = writableDatabase
            writableDB.delete("favorites", "vocabulary=?", arrayOf(vocabulary))
        } catch (e: Exception) {
            e.printStackTrace()
            result = false
        }

        return result
    }

    fun isExists(vocabulary: String): Boolean {
        var result = true
        var index = 0

        try {
            val readable = readableDatabase
            val cursor = readable.rawQuery("SELECT vocabulary from favorites WHERE vocabulary=?", arrayOf(vocabulary))
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                index++
                cursor.moveToNext()
            }
            cursor.close()
            if (index == 0)
                result = false
        } catch (e: Exception) {
            e.printStackTrace()
            result = false
        }

        Log.d("isExists", result.toString())
        return result
    }

}