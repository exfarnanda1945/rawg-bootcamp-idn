package com.example.rawgbootcampidn.data.database

import androidx.room.TypeConverter
import com.example.rawgbootcampidn.model.GameResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GameTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun gameDataToString(game:GameResult): String {
        return gson.toJson(game)
    }

    @TypeConverter
    fun gameStringToData(string: String): GameResult {
        val listType = object : TypeToken<GameResult>() {}.type
        return gson.fromJson(string, listType)
    }
}