package com.example.rawgbootcampidn.model


import com.google.gson.annotations.SerializedName

data class ScreenshotsResult(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?
)