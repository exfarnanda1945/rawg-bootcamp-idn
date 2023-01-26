package com.example.rawgbootcampidn.data.database

import androidx.room.TypeConverter
import com.example.rawgbootcampidn.model.GameDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GameTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun gameDataToString(game:GameDetail): String {
        return gson.toJson(game)
    }

    @TypeConverter
    fun gameStringToData(string: String): GameDetail {
        val listType = object : TypeToken<GameDetail>() {}.type
        return gson.fromJson(string, listType)
    }
}