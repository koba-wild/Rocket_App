package com.example.dragonx.models

import com.beust.klaxon.JsonArray
import com.denzcoskun.imageslider.models.SlideModel
import java.net.URL

class Rocket {
    @JvmField
    var name: String? = null
    @JvmField
    var description: String? = null
    @JvmField
    var wikipedia: String? = null
    @JvmField
    var height: String? = null
    @JvmField
    var mass: String? = null
    @JvmField
    var first_flight: String? = null
    @JvmField
    var flickr_images: String? = null
    @JvmField
    val imageList = ArrayList<SlideModel>()
}