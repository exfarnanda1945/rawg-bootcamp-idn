package com.example.rawgbootcampidn.data

import android.util.Log
import com.example.rawgbootcampidn.data.network.api.GameApi
import com.example.rawgbootcampidn.data.network.handler.NetworkResult
import com.example.rawgbootcampidn.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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
                Log.d("apiServiceError", "statusCode:${games.code()}, message:${games.message()}")
                emit(NetworkResult.Error("Failed to fetch data from server."))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("dataSourceError", "${e.message}")
            emit(NetworkResult.Error("Something went wrong. Please check log."))
        }
    }
}