package com.example.rawgbootcampidn.data

import com.example.rawgbootcampidn.data.database.GameDao
import com.example.rawgbootcampidn.data.database.GameEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: GameDao) {
    suspend fun insertGame(gameEntity: GameEntity) = dao.insertGame(gameEntity)
    fun listGame(): Flow<List<GameEntity>> = dao.listGame()
    suspend fun deleteGame(gameEntity: GameEntity) = dao.deleteGame(gameEntity)
    suspend fun deleteAllGame() = dao.deleteAllGame()

}