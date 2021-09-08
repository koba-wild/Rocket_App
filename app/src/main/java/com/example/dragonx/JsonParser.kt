package com.example.dragonx

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.dragonx.models.Rocket
import java.net.URL
import kotlinx.coroutines.launch

class JsonParser {
    var rockets = arrayListOf<Rocket>()
    val images = arrayListOf("https://live.staticflickr.com/8578/16655995541_7817565ea9_k.jpg", "https://farm8.staticflickr.com/7647/16581815487_6d56cb32e1_b.jpg")

    private fun parseJson () : JsonArray<JsonObject> {
        val result = URL("https://api.spacexdata.com/v4/dragons").readText()
        val parser: Parser = Parser()
        val stringBuilder: StringBuilder = StringBuilder(result)
        val jsonArray = parser.parse(stringBuilder) as JsonArray<JsonObject>
        return jsonArray
    }

    suspend fun getRocketData(): ArrayList<Rocket> {
        lateinit var rocket: Rocket
        val parsedData = parseJson ()
        for (i in 0 until parsedData.size) {
            rocket = Rocket()
            rocket.name = parsedData.string("name")[i]
            rocket.year = parsedData.string("first_flight")[i]
            rocket.image = URL(images[i])
            rockets.add(rocket)
        }
        return rockets
    }
}