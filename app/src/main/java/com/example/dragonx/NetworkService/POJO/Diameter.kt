package com.example.dragonx.NetworkService.POJO

import com.google.gson.annotations.SerializedName

data class Diameter (
    @SerializedName("meters") val meters: Double?,
    @SerializedName("feet") val feet: Int?
)