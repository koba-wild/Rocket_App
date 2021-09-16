package com.example.dragonx.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.dragonx.models.Rocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import java.net.URL

class RocketListViewModel() : ViewModel() {
    var rocketsData = MutableLiveData<List<Rocket>>()

    fun getRockets() {
        CoroutineScope(Dispatchers.IO).launch {
            rocketsData.postValue(getRocketData()!!)
        }
    }

    private fun parseJson () : JsonArray<JsonObject> {
        val result = URL("https://api.spacexdata.com/v4/dragons").readText()
        val parser: Parser = Parser()
        val stringBuilder: StringBuilder = StringBuilder(result)
        return parser.parse(stringBuilder) as JsonArray<JsonObject>
    }

    suspend fun getRocketData(): ArrayList<Rocket> {
        val rockets = arrayListOf<Rocket>()
        val parsedData = parseJson ()
        for (i in 0 until parsedData.size) {
            val rocket = Rocket()
            rocket.name = parsedData.string("name")[i]
            rocket.first_flight = parsedData.string("first_flight")[i]
            var flickr_images = parsedData["flickr_images"][i] as JsonArray<String>
            rocket.flickr_images = flickr_images[0]
            rockets.add(rocket)
        }
        return rockets
    }
}