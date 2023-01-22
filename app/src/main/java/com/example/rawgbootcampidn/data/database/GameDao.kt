package com.example.rawgbootcampidn.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(gameEntity: GameEntity)

    @Query("SELECT * FROM game_table ORDER BY id ASC")
    fun listGame(): Flow<List<GameEntity>>

    @Delete()
    suspend fun deleteGame(gameEntity: GameEntity)

    @Query("DELETE FROM game_table")
    suspend fun deleteAllGame()
}