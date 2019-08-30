package com.indieteam.englishvocabulary.business.provider

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.indieteam.englishvocabulary.model.AccountModel
import com.indieteam.englishvocabulary.model.FavouriteModel
import com.indieteam.englishvocabulary.model.RemindNotificationModel
import javax.inject.Singleton

@Singleton
class DatabaseManager(context: Context) : DatabaseProvider(context) {

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
                    "",
                    cursor.getString(cursor.getColumnIndex("synced"))
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
            value.put("synced", favouriteModel.synced)

            writableDB.insert("favorites", null, value)

        } catch (e: Exception) {
            e.printStackTrace()
            result = false
        }

        Log.d("insertVocabulary", result.toString())

        return result
    }

    fun getVocabularyIDByName(vocabulary: String): Int? {
        var result: Int? = null

        if (!isVocabularyExists(vocabulary))
            return result

        try {
            val readableDB = readableDatabase
            val cursor = readableDB.rawQuery("SELECT id, vocabulary FROM favorites WHERE vocabulary=?", arrayOf(vocabulary))
            cursor.moveToFirst()
            result = cursor.getString(cursor.getColumnIndex("id")).toInt()
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.d("vocabularyIDByName", result.toString())
        return result
    }

    fun updateVocabularySyncedByName(vocabulary: String, synced: Boolean): Boolean {
        var result = true

        try {
            val writableDB = writableDatabase
            val contentValue = ContentValues()
            if(synced)
                contentValue.put("synced", "synced")
            else
                contentValue.put("synced", "")
            writableDB.update("favorites", contentValue,"vocabulary=?", arrayOf(vocabulary))
        }catch (e: Exception) {
            result = false
            e.printStackTrace()
        }

        Log.d("updateVocabularySync", result.toString())

        return result
    }

    fun getVocabularySyncedByName(vocabulary: String): String? {
        var result: String? = null

        if (!isVocabularyExists(vocabulary))
            return result

        try {
            val readableDB = readableDatabase
            val cursor = readableDB.rawQuery("SELECT vocabulary, synced FROM favorites WHERE vocabulary=?", arrayOf(vocabulary))
            cursor.moveToFirst()
            result = cursor.getString(cursor.getColumnIndex("synced"))
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.d("vocabularySyncedByName", result.toString())
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

    fun deleteAllVocabulary(): Boolean {
        var result = true

        try {
            val writableDB = writableDatabase
            writableDB.delete("favorites", null, null)
        } catch (e: Exception) {
            result = false
        }

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
            val cursor = readableDB.rawQuery("SELECT rate FROM notification_settings", null)
            cursor.moveToFirst()

            val ratedSaved = cursor.getString(cursor.getColumnIndex("rate"))
            if (!ratedSaved.isNullOrEmpty())
                result = ratedSaved.toInt()
            Log.d("getRateSetting", result.toString())

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
                        if (cursor.getString(cursor.getColumnIndex("description")).isNullOrEmpty())
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
            writableDB.delete("account", null, null)
        } catch (e: Exception) {
            result = false
        }

        Log.d("deleteAccount", result.toString())

        return result
    }

    fun insertAccount(accountModel: AccountModel): Boolean {
        var result = true
        try {
            deleteAccount()
            val writableDB = writableDatabase
            val contentValue = ContentValues()
            contentValue.put("email", accountModel.email)
            contentValue.put("accID", accountModel.accID)
            contentValue.put("type", accountModel.type)
            contentValue.put("description", accountModel.description)
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

    fun getAccID(): String? {
        var accID: String? = null
        try {
            val readableDB = readableDatabase
            val cursor = readableDB.rawQuery("SELECT accID FROM account", null)
            cursor.moveToFirst()
            accID = cursor.getString(cursor.getColumnIndex("accID"))
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        Log.d("accID logged", accID.toString())
        return accID
    }
}