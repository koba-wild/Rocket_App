package com.example.dragonx.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.dragonx.models.Rocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class RocketListViewModel : ViewModel() {
    var rockets = arrayListOf<Rocket>()
    val images = arrayListOf("https://live.staticflickr.com/8578/16655995541_7817565ea9_k.jpg", "https://farm8.staticflickr.com/7647/16581815487_6d56cb32e1_b.jpg")

    init {
        Log.i("JsonParser", "JsonParser for RocketListFragment was created")
    }

    private fun parseJson () : JsonArray<JsonObject> {
        val result = URL("https://api.spacexdata.com/v4/dragons").readText()
        val parser: Parser = Parser()
        val stringBuilder: StringBuilder = StringBuilder(result)
        return parser.parse(stringBuilder) as JsonArray<JsonObject>
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