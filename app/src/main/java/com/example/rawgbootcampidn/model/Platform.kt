package com.example.rawgbootcampidn.model


import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)