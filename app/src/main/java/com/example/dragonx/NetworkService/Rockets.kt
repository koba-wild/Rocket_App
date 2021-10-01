package com.example.dragonx.NetworkService

import com.google.gson.annotations.SerializedName
data class Rockets (val rockets: List<Rocket>)
data class Rocket (
    var name: String? = null,
    var description: String? = null,
    var wikipedia: String? = null,
    var diameter: Diameter,
    @SerializedName("dry_mass_kg") var massKg: Int? = null,
    @SerializedName("dry_mass_lb") var massLb: Int? = null,
    @SerializedName("first_flight") var firstFlight: String? = null,
    @SerializedName("flickr_images") var flickrImages: ArrayList<String>? = null,
)

data class Diameter (
    val meters: Double? = null,
    val feet: Int? = null
)
