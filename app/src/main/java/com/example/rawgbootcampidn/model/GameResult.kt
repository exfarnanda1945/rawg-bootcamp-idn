package com.example.rawgbootcampidn.model


import com.google.gson.annotations.SerializedName

data class GameResult(
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatform?>?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("released")
    val released: String?
)