package com.example.dragonx.util

import com.denzcoskun.imageslider.models.SlideModel

data class RocketModel (
    @JvmField
    var name: String? = null,
    var description: String? = null,
    @JvmField
    var wikipedia: String? = null,
    @JvmField
    var heightDiameter: Double? = null,
    var heightFeet: Int? = null,
    @JvmField
    var massKg: Int? = null,
    var massLb: Int? = null,
    @JvmField
    var firstFlight: String? = null,
    @JvmField
    var flickrImages: String? = null,
    @JvmField
    val imageList: ArrayList<SlideModel> = ArrayList<SlideModel>()
)