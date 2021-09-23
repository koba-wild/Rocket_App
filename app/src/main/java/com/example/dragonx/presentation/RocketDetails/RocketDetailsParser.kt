package com.example.dragonx.presentation.RocketDetails

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dragonx.models.Rocket
import java.net.URL

class RocketDetailsParser {
    fun parseJson (rocketNumber:Int?): Rocket {
        lateinit var jsonArray: JsonArray<JsonObject>
        val result = URL("https://api.spacexdata.com/v4/dragons").readText()
        val parser: Parser = Parser()
        val stringBuilder: StringBuilder = StringBuilder(result)
        jsonArray = parser.parse(stringBuilder) as JsonArray<JsonObject>
        val rocket = Rocket()
        rocket.name = jsonArray.string("name")[rocketNumber!!]
        rocket.description = jsonArray.string("description")[rocketNumber]
        rocket.wikipedia = jsonArray.string("wikipedia")[rocketNumber]
        rocket.heightDiameter =
            jsonArray.obj("diameter")[rocketNumber]?.double("meters")
        rocket.heightFeet =  jsonArray.obj("diameter")[rocketNumber]?.int("feet")
        rocket.massKg =
            jsonArray.int("dry_mass_kg")[rocketNumber]
        rocket.massLb = jsonArray.int("dry_mass_lb")[rocketNumber]
        rocket.firstFlight = jsonArray.string("first_flight")[rocketNumber]
        val flickr_images = jsonArray.get("flickr_images")[rocketNumber] as JsonArray<String>
        var arr = arrayOfNulls<String>(flickr_images.size)
        for (i in 0 until flickr_images.size) {
            arr[i] = flickr_images[i]
        }
        arr.forEach {
            rocket.imageList.add(
                SlideModel(it)
            )
        }
        return rocket
    }
}