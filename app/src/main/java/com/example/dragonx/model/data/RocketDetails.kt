package com.example.dragonx.model.data

import com.denzcoskun.imageslider.models.SlideModel

data class RocketDetails(
    var name: String?,
    var description: String?,
    var wikipedia: String?,
    var heightDiameter: Double?,
    var heightFeet: Int?,
    var massKg: Int?,
    var massLb: Int?,
    var firstFlight: String?,
    var flickrImages: List<String>
)