package com.example.rawgbootcampidn.data

import android.util.Log
import com.example.rawgbootcampidn.data.network.api.GameApi
import com.example.rawgbootcampidn.data.network.handler.NetworkResult
import com.example.rawgbootcampidn.model.Game
import com.example.rawgbootcampidn.model.GameDetail
import com.example.rawgbootcampidn.model.Screenshots
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val gameApi: GameApi) {
    suspend fun getGameList(queries: Map<String, String>): Flow<NetworkResult<Game>> = flow {
        try {
            emit(NetworkResult.Loading(true))
            val games = gameApi.getGameList(queries)


            // request data successful
            if (games.isSuccessful) {
                val responseBody = games.body()
                // if data empty
                if (responseBody?.results?.isEmpty() == true) {
                    emit(NetworkResult.Error("List game not found."))
                } else {
                    emit(NetworkResult.Success(responseBody!!))
                }
            } else {
                // request data failed
                Log.d("apiServiceError", "statusCode:${news.code()}, message:${news.message()}")
                emit(NetworkResult.Error("Failed to fetch data from server."))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("remoteError", "${e.message}")
            emit(NetworkResult.Error("Something went wrong. Please check log."))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getGameDetailById(gameId: Int): Flow<NetworkResult<GameDetail>> = flow {
        try {
            emit(NetworkResult.Loading(true))
            val gameDetail = gameApi.getGameDetailById(gameId)
            if (gameDetail.isSuccessful) {
                val responseBody = gameDetail.body()

                if (responseBody != null) {
                    emit(NetworkResult.Success(responseBody))
                } else {
                    emit(NetworkResult.Error("Can't fetch detail game."))
                }
            } else {
                // request data failed
                Log.d(
                    "apiServiceError",
                    "statusCode:${gameDetail.code()}, message:${gameDetail.message()}"
                )
                emit(NetworkResult.Error("Failed to fetch data from server."))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("remoteError", "${e.message}")
            emit(NetworkResult.Error("Something went wrong. Please check log."))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getGameScreenshotById(gameId: Int): Flow<NetworkResult<Screenshots>> = flow {
        try {
            emit(NetworkResult.Loading(true))
            val screenshots = gameApi.getGameScreenshots(gameId)

            if (screenshots.isSuccessful) {
                val responseBody = screenshots.body()
                if (responseBody?.results?.isEmpty() == true) {
                    emit(NetworkResult.Error("Screenshots game not found"))
                } else {
                    emit(NetworkResult.Success(responseBody!!))
                }
            } else {
                Log.d(
                    "apiServiceError",
                    "statusCode:${screenshots.code()}, message:${screenshots.message()}"
                )
                emit(NetworkResult.Error("Failed to fetch data from server."))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("remoteError", "${e.message}")
            emit(NetworkResult.Error("Something went wrong. Please check log."))
        }
    }
}