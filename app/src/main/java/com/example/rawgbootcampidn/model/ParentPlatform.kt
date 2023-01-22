package com.example.rawgbootcampidn.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParentPlatform(
    @SerializedName("platform")
    val platform: Platform?
):Parcelable