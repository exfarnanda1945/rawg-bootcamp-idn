package com.example.rawgbootcampidn.model


import com.google.gson.annotations.SerializedName

data class Screenshots(
    @SerializedName("results")
    val results: List<ScreenshotsResult?>?
)