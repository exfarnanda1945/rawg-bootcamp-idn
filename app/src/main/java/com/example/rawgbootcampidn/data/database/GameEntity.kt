package com.example.rawgbootcampidn.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rawgbootcampidn.model.GameResult
import com.example.rawgbootcampidn.utils.Constant.GAME_TABLE_NAME

@Entity(tableName = GAME_TABLE_NAME)
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val game: GameResult
)