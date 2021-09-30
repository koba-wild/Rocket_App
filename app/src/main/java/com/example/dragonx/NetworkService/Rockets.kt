package com.example.dragonx.NetworkService

import com.beust.klaxon.JsonArray
import com.denzcoskun.imageslider.models.SlideModel
import com.squareup.moshi.Json
data class Rockets (val rockets: List<Rocket>)
data class Rocket (
    @field:Json(name = "name") var name: String? = null,
    @field:Json(name = "description") var description: String? = null,
    @field:Json(name = "wikipedia") var wikipedia: String? = null,
    @field:Json(name = "diameter") var diameter: Diameter,
    @field:Json(name = "dry_mass_kg") var massKg: Int? = null,
    @field:Json(name = "dry_mass_lb") var massLb: Int? = null,
    @field:Json(name = "first_flight") var firstFlight: String? = null,
    @field:Json(name = "flickr_images") var flickrImages: String? = null,
)

data class Diameter (
    @field:Json(name = "meters") val meters: Double? = null,
    @field:Json(name = "feet") val feet: Int? = null
)
