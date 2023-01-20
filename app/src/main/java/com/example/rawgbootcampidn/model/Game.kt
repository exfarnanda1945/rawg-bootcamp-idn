package com.example.rawgbootcampidn.model


import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("results")
    val results: List<GameResult?>?
)