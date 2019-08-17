package com.indieteam.englishvocabulary.business.provider

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.indieteam.englishvocabulary.model.AccountModel
import com.indieteam.englishvocabulary.model.FavouriteModel
import com.indieteam.englishvocabulary.model.RemindNotificationModel
import com.indieteam.englishvocabulary.view.App
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseManager(context: Context) : DatabaseProvider(context) {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var randomProvider: RandomProvider

    fun getFavorites(): ArrayList<FavouriteModel.Item> {
        val result = ArrayList<FavouriteModel.Item>()

        try {
            val readable = readableDatabase
            val cursor = readable.rawQuery("SELECT * FROM favorites ORDER BY id DESC", null)
            cursor.moveToFirst()

            while (!cursor.isAfterLast && cursor != null) {
                val item = FavouriteModel.Item(
                    cursor.getString(cursor.getColumnIndex("id")).toInt(),
                    null,
                    cursor.getString(cursor.getColumnIndex("vocabulary")),
                    cursor.getString(cursor.getColumnIndex("vi")),
                    ""
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

    fun insertVocabulary(favouriteModel: FavouriteModel.Item): Boolean {
        var result = true

        try {
            if (isVocabularyExists(favouriteModel.vocabulary))
                return false

            val writableDB = writableDatabase
            val value = ContentValues()
            value.put("vocabulary", favouriteModel.vocabulary)
            value.put("vi", favouriteModel.vi)
            value.put("description", favouriteModel.description)
            value.put("firebaseID", randomProvider.randomID())

            writableDB.insert("favorites", null, value)

        } catch (e: Exception) {
            e.printStackTrace()
            result = false
        }

        Log.d("insertVocabulary", result.toString())

        return result
    }

    fun deleteVocabularyByName(vocabulary: String): Boolean {
        var result = true

        try {
            if (!isVocabularyExists(vocabulary))
                return false

            val writableDB = writableDatabase
            writableDB.delete("favorites", "vocabulary=?", arrayOf(vocabulary))
        } catch (e: Exception) {
            e.printStackTrace()
            result = false
        }

        Log.d("deleteVocabularyByName", result.toString())

        return result
    }

    fun isVocabularyExists(vocabulary: String): Boolean {
        var result = true
        var index = 0

        try {
            val readable = readableDatabase
            val cursor = readable.rawQuery("SELECT vocabulary from favorites WHERE vocabulary=?", arrayOf(vocabulary))
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                index++
                break
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
            result = false
        }

        if (index == 0)
            result = false

        Log.d("isVocabularyExists", result.toString())
        return result
    }

    private fun rateIsNotEmpty(): Boolean {
        var result = true
        var index = 0

        try {
            val readable = readableDatabase
            val cursor = readable.rawQuery("SELECT * FROM notification_settings", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                index++
                break
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
            result = false
        }

        if (index == 0)
            result = false

        Log.d("rateIsNotEmpty", result.toString())
        return result
    }

    fun getRateSetting(): Int? {
        var result: Int? = null
        try {
            val readableDB = readableDatabase
            val cursor = readableDB.rawQuery("SELECT * FROM notification_settings", null)
            cursor.moveToFirst()

            result = cursor.getString(cursor.getColumnIndex("rate")).toInt()
            Log.d("data", result.toString())

            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }

    fun getRateId(): String? {
        var result: String? = null
        try {
            val readableDB = readableDatabase
            val cursor = readableDB.rawQuery("SELECT * FROM notification_settings", null)
            cursor.moveToFirst()

            while (!cursor.isAfterLast) {
                result = cursor.getString(cursor.getColumnIndex("id"))
                Log.d("data", result.toString())
                cursor.moveToNext()
            }

            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }

    fun updateRateSetting(id: String, rate: Int): Boolean {
        var result = true

        try {
            val writableDB = writableDatabase
            val contentValues = ContentValues()
            contentValues.put("rate", rate)
            writableDB.update("notification_settings", contentValues, "id=?", arrayOf(id))
        } catch (e: Exception) {
            result = false
            e.printStackTrace()
        }

        Log.d("updateRateSetting", result.toString())

        return result
    }

    fun insertRateSettingDefault(): Boolean {
        var result = true

        if (rateIsNotEmpty()) {
            result = false
        } else {
            try {
                val writableDB = writableDatabase
                val contentValues = ContentValues()
                contentValues.put("rate", "5")
                writableDB.insert("notification_settings", null, contentValues)
            } catch (e: Exception) {
                result = false
                e.printStackTrace()
            }
        }

        Log.d("insertRateSettingDef", result.toString())

        return result
    }

    fun randomVocabulary(): RemindNotificationModel? {
        val results = ArrayList<RemindNotificationModel>()

        try {
            val readable = readableDatabase
            val cursor = readable.rawQuery("SELECT * FROM favorites", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {

                results.add(
                    RemindNotificationModel(
                        cursor.getString(cursor.getColumnIndex("vocabulary")),
                        cursor.getString(cursor.getColumnIndex("vi")),
                        if (cursor.getString(cursor.getColumnIndex("description")).isNullOrBlank())
                            ""
                        else
                            cursor.getString(cursor.getColumnIndex("description"))
                    )
                )
                cursor.moveToNext()
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return if (results.size > 0)
            results[(0 until results.size).random()]
        else
            null
    }

    fun deleteAccount(): Boolean {
        var result = true

        try {
            val writableDB = writableDatabase
            writableDB.delete("account",null, null)
        } catch (e: Exception) {
            result = false
        }

        Log.d("deleteAccount", result.toString())

        return result
    }

    fun insertAccount(acccoutModel: AccountModel): Boolean {
        var result = true
        try {
            deleteAccount()
            val writableDB = writableDatabase
            val contentValue = ContentValues()
            contentValue.put("email", acccoutModel.email)
            contentValue.put("type", acccoutModel.type)
            contentValue.put("description", acccoutModel.description)
            writableDB.insert("account", null, contentValue)
        } catch (e: Exception) {
            result = false
        }

        Log.d("insertAccount", result.toString())

        return result
    }

    fun getEmail(): String? {
        var email: String? = null
        try {
            val readableDB = readableDatabase
            val cursor = readableDB.rawQuery("SELECT email FROM account", null)
            cursor.moveToFirst()
            email = cursor.getString(cursor.getColumnIndex("email"))
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        Log.d("Email logged", email.toString())
        return email
    }
 }