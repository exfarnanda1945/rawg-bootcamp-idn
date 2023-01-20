package com.example.rawgbootcampidn.data.network.api

import com.example.rawgbootcampidn.model.Game
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GameApi {
    @GET("games")
    suspend fun getGameList(
        @QueryMap queries: Map<String, String>
    ): Response<Game>
}