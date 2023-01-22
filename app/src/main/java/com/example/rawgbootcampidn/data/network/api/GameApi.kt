package com.example.rawgbootcampidn.data.network.api

import com.example.rawgbootcampidn.model.Game
import com.example.rawgbootcampidn.model.GameDetail
import com.example.rawgbootcampidn.model.Screenshots
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface GameApi {
    @GET("games")
    suspend fun getGameList(
        @QueryMap queries: Map<String, String>
    ): Response<Game>

    @GET("games/{gameId}")
    suspend fun getGameDetailById(
        @Path("gameId") gameId:Int
    ):Response<GameDetail>

    @GET("games/{gameId}/screenshots")
    suspend fun getGameScreenshots(
        @Path("gameId") gameId: Int
    ):Response<Screenshots>

}