package com.example.rawgbootcampidn.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rawgbootcampidn.model.GameDetail
import com.example.rawgbootcampidn.utils.Constant.GAME_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Entity(tableName = GAME_TABLE_NAME)
@Parcelize
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val game: GameDetail
):Parcelable