package com.example.dragonx.model.data.JsonObjects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Diameter (
    @SerializedName("meters") val meters: Double?,
    @SerializedName("feet") val feet: Int?
) : Parcelable