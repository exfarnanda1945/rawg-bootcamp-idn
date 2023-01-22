package com.example.rawgbootcampidn.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameDetail(
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("background_image_additional")
    val backgroundImageAdditional: String?,
    @SerializedName("description_raw")
    val descriptionRaw: String?,
    @SerializedName("developers")
    val developers: List<Developer>?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatform>?,
    @SerializedName("publishers")
    val publishers: List<Publisher>?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("website")
    val website: String?
):Parcelable