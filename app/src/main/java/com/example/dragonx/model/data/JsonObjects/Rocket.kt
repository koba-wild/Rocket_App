package com.example.dragonx.model.data.JsonObjects

import com.google.gson.annotations.SerializedName

data class Rocket (
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("wikipedia") val wikipedia: String?,
    @SerializedName("diameter") val diameter: Diameter,
    @SerializedName("dry_mass_kg") val massKg: Int?,
    @SerializedName("dry_mass_lb") val massLb: Int?,
    @SerializedName("first_flight") val firstFlight: String?,
    @SerializedName("flickr_images") val flickrImages: ArrayList<String>?
)
