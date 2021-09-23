package com.example.dragonx.presentation.RocketList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.dragonx.models.Rocket
import com.example.dragonx.util.Constants
import java.net.URL

enum class ApiStatus {LOADING, ERROR, DONE}

class RocketListParser {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    fun parseJson () : JsonArray<JsonObject>? {
        val parser: Parser = Parser()
        try {
            _status.postValue(ApiStatus.LOADING)
            val result = URL(Constants.URL_STRING).readText()
            val stringBuilder: StringBuilder = StringBuilder(result)
            val myParsedObject = parser.parse(stringBuilder) as JsonArray<JsonObject>
            _status.postValue(ApiStatus.DONE)
            return myParsedObject
        } catch (e: Exception){
            _status.postValue(ApiStatus.ERROR)
            return null
        }
    }

    fun getRocketData(): ArrayList<Rocket> {
        val rockets = arrayListOf<Rocket>()
        val parsedData = parseJson()
        if (parsedData != null) {
            for (i in 0 until parsedData.size) {
                val rocket = Rocket()
                rocket.name = parsedData.string("name")[i]
                rocket.firstFlight = parsedData.string("first_flight")[i]
                var flickr_images = parsedData["flickr_images"][i] as JsonArray<String>
                rocket.flickrImages = flickr_images[0]
                rockets.add(rocket)
            }
        }
        return rockets
    }
}